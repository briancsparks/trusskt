package net.cdr0.trusskt.kind

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.kvo.KvObject
import net.cdr0.trusskt.utils.cross.Sys

open class LogBase(
  val truss: Truss
) {

  val data = KvObject()
  val metaData = hashMapOf<String, String>()

  private var tick: Long = Sys.currentTimeMillis() - Truss.startMillis

  // ------------------------------------------------------------------------------------------------------------------
  init {
    if (truss.useTick()) {
      data.putIt("tick", tick)
    }
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun getMeta(key: String): String {
    return metaData.getOrElse(key) { "" }
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun hasMeta(key: String): Boolean {
    return metaData.containsKey(key)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun modName(): String {
    return truss.moduleName
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun TAG(): String {
    return modName()
  }

}
