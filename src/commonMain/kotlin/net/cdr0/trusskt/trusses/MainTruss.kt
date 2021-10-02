package net.cdr0.trusskt.trusses

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.TrussControlMsg

class MainTruss(
  appId: String,
  var sessionId: String = "NOSESSIONID",
): Truss("MainTruss") {

  // ------------------------------------------------------------------------------------------------------------------
  init {
    Truss.mainTruss           = this
    Truss.appId               = appId
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun onControl(msg: TrussControlMsg): Boolean {
    return super.onControl(msg)
  }


}
