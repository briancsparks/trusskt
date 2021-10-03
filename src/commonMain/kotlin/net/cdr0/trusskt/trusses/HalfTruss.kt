package net.cdr0.trusskt.trusses

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.TrussControlMsg
import net.cdr0.trusskt.kind.LogAttr
import net.cdr0.trusskt.kind.LogItem
import net.cdr0.trusskt.utils.Constants.Companion.DEBUG
import net.cdr0.trusskt.utils.Constants.Companion.DEFAULT_RATE_LIMIT
import net.cdr0.trusskt.utils.Constants.Companion.INFO
import net.cdr0.trusskt.utils.Constants.Companion.VERBOSE
import net.cdr0.trusskt.utils.Constants.Companion.WARN
import net.cdr0.trusskt.utils.Constants.Companion.ERROR

class HalfTruss(
  moduleName: String,
): Truss(moduleName) {

  init {
    mainTruss?.setCtlDestination(this)
  }


  // ==========================================================================================================================================
  // LogItem

  // ------------------------------------------------------------------------------------------------------------------
  fun logv(): LogItem {
    return log_x(VERBOSE)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun logd(): LogItem {
    return log_x(DEBUG)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun logi(): LogItem {
    return log_x(INFO)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun event(): LogItem {
    return logi()
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun logw(message: String): LogItem {
    return log_x(WARN).log("message", message)
  }

  // ------------------------------------------------------------------------------------------------------------------
  // TODO: Add exception object
  fun loge(message: String): LogItem {
    return log_x(ERROR).log("message", message)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun TODO(message: String) {
    logw(message).end()
  }



  // ==========================================================================================================================================
  // Rate-limited versions
  // ------------------------------------------------------------------------------------------------------------------
  fun logv(rateLimitId: Int): LogItem {
    return log_xyz(VERBOSE, rateLimitId, -1)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun logv(rateLimitId: Int, rateLimit: Int): LogItem {
    return log_xyz(VERBOSE, rateLimitId, rateLimit)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun logd(rateLimitId: Int): LogItem {
    return log_xyz(DEBUG, rateLimitId, -1)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun logd(rateLimitId: Int, rateLimit: Int): LogItem {
    return log_xyz(DEBUG, rateLimitId, rateLimit)
  }


  // ==========================================================================================================================================
  // LogAttr
  // ------------------------------------------------------------------------------------------------------------------
  fun <T> attr(entName: String, id: T): LogAttr {
    return attr_x(entName, id)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> ent(entName: String, id: T): LogAttr {
    return attr_x(entName, id)
  }




  // ==========================================================================================================================================
  // onControl details

  // ------------------------------------------------------------------------------------------------------------------
  fun control(msg: TrussControlMsg) {
    mainTruss?.onControl(msg)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun onControl(msg: TrussControlMsg): Boolean {
    return false
  }



  // ==========================================================================================================================================
  // Implementation details for the 'log' part of: tr().log("key",value).end()
  //                                                    ^^^

  // ------------------------------------------------------------------------------------------------------------------
  fun log_x(level: Int): LogItem {
    val item = LogItem(this)
    item.level = level
    return fixupLogItem(item)
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun fixupLogItem(item: LogItem): LogItem {
    // TODO: fixup (long output, short output, etc.)
    return item
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun log_xyz(level: Int, rateLimitId: Int, rateLimit: Int): LogItem {
    var myRateLimit = rateLimit
    if (rateLimitId > 0) {
      if (myRateLimit == -1) {
        myRateLimit = DEFAULT_RATE_LIMIT
      }

      // TODO: Implement rate limit
    }

//    return log_x(level)
    // TODO: Remove this version of log_x and really implement rateLimit
    return log_x(level, myRateLimit)
  }

  // ------------------------------------------------------------------------------------------------------------------
  // TODO: Remove this
  fun log_x(level: Int, rateLimit: Int): LogItem {
    val item = LogItem(this)
    item.level = level
    item.rateLimit = rateLimit
    return fixupLogItem(item)
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun <T> attr_x(entName: String, id: T): LogAttr {
    val attr = LogAttr(this)
    return fixupLogAttr(attr).ent(entName, id)
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun fixupLogAttr(attr: LogAttr): LogAttr {
    // TODO: fixup (long output, short output, etc.)
    return attr
  }





  // ==========================================================================================================================================
  // ------------------------------------------------------------------------------------------------------------------
  override fun addLogItem(item: LogItem) {
    mainTruss?.addLogItem(item)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun sendLogItem(item: LogItem) {
    mainTruss?.sendLogItem(item)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun addLogAttr(attr: LogAttr) {
    mainTruss?.addLogAttr(attr)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun sendLogAttr(attr: LogAttr) {
    mainTruss?.sendLogAttr(attr)
  }

}

