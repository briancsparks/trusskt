package net.cdr0.trusskt.trusses

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.TrussControlMsg
import net.cdr0.trusskt.kind.LogAttr
import net.cdr0.trusskt.kind.LogItem

class ConsoleTruss(
  private val sourceTruss: Truss
): Truss("ConsoleTruss") {

  init {
    sourceTruss.setDestination(this)
    sourceTruss.setSendDestination(this)
    sourceTruss.setCtlDestination(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun onControl(msg: TrussControlMsg): Boolean {
    return false
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun addLogItem(item: LogItem) {
    println("${item.TAG()}: ${item.toString()}")
    //System.out.printf("%s: %s\n", item.TAG(), item.toString())
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun sendLogItem(item: LogItem) {
    println("${item.TAG()}: send LogItem: ${item.toString()}")
    //System.out.printf("%s: LogItem: (%s|%s|%s|%s) -- %s\n", item.TAG(), s, s2, s3, s4, item.toString())
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun addLogAttr(attr: LogAttr) {
    println("${attr.TAG()}: ${attr.toString()}")
    //System.out.printf("%s: %s\n", attr.TAG(), attr.toString())
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun sendLogAttr(attr: LogAttr) {
    println("${attr.TAG()}: send LogAttr: ${attr.toString()}")
    //System.out.printf("%s: LogItem: (%s|%s|%s|%s) -- %s\n", attr.TAG(), s, s2, s3, s4, attr.toString())
  }

}
