package net.cdr0.trusskt.trusses

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.TrussControlMsg

class ArrayListTruss(

): Truss("ArrayListTruss") {

  val output = arrayListOf<String>()

  // ------------------------------------------------------------------------------------------------------------------
  override fun onControl(msg: TrussControlMsg): Boolean {
    return super.onControl(msg)
  }

}

