package net.cdr0.trusskt

import net.cdr0.trusskt.kind.LogAttr
import net.cdr0.trusskt.kind.LogItem
import net.cdr0.trusskt.trusses.MainTruss
import net.cdr0.trusskt.utils.cross.Sys
import kotlin.native.concurrent.ThreadLocal

open class TrussControlMsg
class TrussFlushMsg: TrussControlMsg()

// ==========================================================================================================================================
open class Truss(
  val moduleName: String,
) {

  @ThreadLocal
  companion object {

    // Keep track of the main Truss
    var mainTruss: MainTruss? = null

    var startMillis: Long = Sys.currentTimeMillis()

    var appId: String? = null
  }

  // Other Trusses to forward messages to
  private var destinations: ArrayList<Truss> = arrayListOf()
  private var sendDestinations: ArrayList<Truss> = arrayListOf()
  private var ctlDestination: ArrayList<Truss> = arrayListOf()

  // ------------------------------------------------------------------------------------------------------------------
  open fun addLogItem(item: LogItem) {
    for (truss in destinations) {
      truss.addLogItem(item)
    }
  }

  // ------------------------------------------------------------------------------------------------------------------
  open fun sendLogItem(item: LogItem) {
    addLogItem(item)
    for (truss in sendDestinations) {
      truss.sendLogItem(item)
    }
  }

  // ------------------------------------------------------------------------------------------------------------------
  open fun addLogAttr(attr: LogAttr) {
    for (truss in destinations) {
      truss.addLogAttr(attr)
    }
  }

  // ------------------------------------------------------------------------------------------------------------------
  open fun sendLogAttr(attr: LogAttr) {
    sendLogAttr(attr)
    for (truss in sendDestinations) {
      truss.sendLogAttr(attr)
    }
  }

  // ------------------------------------------------------------------------------------------------------------------
  open fun onControl(msg: TrussControlMsg): Boolean {
    var result = true
    for (truss in ctlDestination) {
      val res = truss.onControl(msg)
      result = result && res
    }
    return result
  }



  // ------------------------------------------------------------------------------------------------------------------
  fun useTick(): Boolean {
    return false
  }




  // ==========================================================================================================================================
  // ------------------------------------------------------------------------------------------------------------------
  // Mechanics
  // ------------------------------------------------------------------------------------------------------------------
  fun setCtlDestination(destination: Truss) {
    ctlDestination.add(destination)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun setDestination(destination: Truss) {
    destinations.add(destination)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun setSendDestination(destination: Truss) {
    sendDestinations.add(destination)
  }
}