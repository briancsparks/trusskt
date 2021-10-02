package net.cdr0.trusskt.utils.cross

import java.util.regex.Pattern

val reInteger: Pattern  = Pattern.compile("^[0-9]+$")
val reReal: Pattern     = Pattern.compile("^[0-9.]+$")

actual object X {

  // ------------------------------------------------------------------------------------------------------------------
  actual fun matchesInt(value: String): Boolean {
    val m = reInteger.matcher(value)
    return m.find()
  }

  // ------------------------------------------------------------------------------------------------------------------
  actual fun matchesReal(value: String): Boolean {
    val m = reReal.matcher(value)
    return m.find()
  }
}

