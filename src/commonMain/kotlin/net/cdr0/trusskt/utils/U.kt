package net.cdr0.trusskt.utils

class U {
  companion object {

    // ------------------------------------------------------------------------------------------------------------------
    fun separatorFor(count: Int, sep: String = ","): String {
      if (count > 0) {
        return sep
      }
      return ""
    }

  }
}