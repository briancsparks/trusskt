package net.cdr0.trusskt.utils.cross

actual object Sys {
  actual fun currentTimeMillis(): Long {
    return System.currentTimeMillis()
  }
}
