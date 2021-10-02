package net.cdr0.trusskt.kind

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.kvo.KvObject
import net.cdr0.trusskt.utils.Constants

class LogAttr(
  truss: Truss
): LogBase(truss) {

  var theEnt: KvObject? = null

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> ent(key: String, value: T): LogAttr {
    theEnt = KvObject().also {
      it.putIt(key, value)
    }
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> attr(key: String, value: T): LogAttr {
    return put(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun smartAttr(key: String, value: String): LogAttr {
    return smartPut(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun meta(key: String, value: String): LogAttr {
    metaData[key] = value
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun end() {
    if (Constants.SEND_ALL_MODE) {
      truss.sendLogAttr(this)
      return
    }
    truss.addLogAttr(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun send() {
    truss.sendLogAttr(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun toString(): String {
    return data.toValue("ent", theEnt)
  }



  // ==================================================================================================================
  // Internal
  // ------------------------------------------------------------------------------------------------------------------
  private fun <T> put(key: String, value: T): LogAttr {
    data.putIt(key, value)
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun smartPut(key: String, value: String): LogAttr {
    data.smartPutIt(key, value)
    return this
  }

}
