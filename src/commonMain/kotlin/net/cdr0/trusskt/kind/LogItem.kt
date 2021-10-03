package net.cdr0.trusskt.kind

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.utils.Constants.Companion.DEBUG
import net.cdr0.trusskt.utils.Constants.Companion.SEND_ALL_MODE
import net.cdr0.trusskt.utils.U

class LogItem(
  truss: Truss
): LogBase(truss) {

  var level: Int = DEBUG

  var rateLimit: Int = -1

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> log(key: String, value: T): LogItem {
    return put(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun smartLog(key: String, value: String): LogItem {
    return smartPut(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun meta(key: String, value: String): LogItem {
    metaData[key] = value
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun end() {
    if (SEND_ALL_MODE) {
      truss.sendLogItem(this)
      return
    }
    truss.addLogItem(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun send() {
    truss.sendLogItem(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun toString(): String {
    return data.toString()
  }



  // ==================================================================================================================
  // Internal
  // ------------------------------------------------------------------------------------------------------------------
  private fun <T> put(key: String, value: T): LogItem {
    data.putIt(key, value)
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun smartPut(key: String, value: String): LogItem {
    data.smartPutIt(key, value)
    return this
  }

}
