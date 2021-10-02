package net.cdr0.trusskt.utils.cross

actual object X {
  actual fun matchesInt(value: String): Boolean { return false }
  actual fun matchesReal(value: String): Boolean { return false }
}

