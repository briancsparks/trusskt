package net.cdr0.trusskt.kvo

import net.cdr0.trusskt.utils.U.Companion.separatorFor
import net.cdr0.trusskt.utils.cross.X.matchesInt
import net.cdr0.trusskt.utils.cross.X.matchesReal

class KvObject {
  private val items = hashMapOf<String, ValueItem>()

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> putIt(key: String, value: T) {
    when (value) {
      is String ->    { items[key] = ValueItem(value) }
      is Boolean ->   { items[key] = ValueItem(value) }
      is Long ->      { items[key] = ValueItem(value) }
      is Double ->    { items[key] = ValueItem(value) }
    }
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun smartPutIt(key: String, value: String) {
    if (matchesInt(value))              { putIt(key, value.toInt().toLong()) }
    else if (matchesReal(value))        { putIt(key, value.toDouble()) }
    else if (value == "true")           { putIt(key, true) }
    else if (value == "false")          { putIt(key, false) }

    putIt(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun toString(): String {
    return toValue()
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun toValue(): String {
    return toValue(null)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun toValue(mergedKey: String?, merged: KvObject? =null): String {
    val sb = StringBuilder()

    sb.append("{")
    sb.append(valuesAsString(mergedKey, merged))
    sb.append("}")

//    return sb.toString()

    // TODO: Remove above
    return "{${valuesAsString(mergedKey, merged)}}"
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun valuesAsString(mergedKey: String?, merged: KvObject? =null): String {
    val sb = StringBuilder()
    var count = 0

    if (mergedKey != null && merged != null) {
      sb.append("\"$mergedKey\":${merged.toValue()}")
      count += 1
    }

    for ((key, valueItem) in items) {
      sb.append("\"$key\":${valueItem.toValue()}${separatorFor(count)}")
      count += 1
    }

    return sb.toString()
  }

}
