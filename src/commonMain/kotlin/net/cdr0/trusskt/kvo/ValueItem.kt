package net.cdr0.trusskt.kvo

data class ValueItem(
  val value: String,
  val isString: Boolean = true,
) {

  constructor(n: Long): this(n.toString(), false) {}
  constructor(n: Double): this(n.toString(), false) {}
  constructor(n: Boolean): this(n.toString(), false) {}

  fun toValue(): String {
    if (isString) {
      return "\"$value\""
    }
    return value
  }
}
