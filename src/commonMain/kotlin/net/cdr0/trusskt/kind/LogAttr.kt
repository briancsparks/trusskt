package net.cdr0.trusskt.kind

import net.cdr0.trusskt.Truss
import net.cdr0.trusskt.kvo.KvObject
import net.cdr0.trusskt.utils.Constants

class LogAttr(
  truss: Truss
): LogBase(truss) {

  var theEnt: KvObject? = null

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> ent(key: String, value: T): LogAttr {
    theEnt = KvObject().also {
      it.putIt(key, value)
    }
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun <T> attr(key: String, value: T): LogAttr {
    return put(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun smartAttr(key: String, value: String): LogAttr {
    return smartPut(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun meta(key: String, value: String): LogAttr {
    metaData[key] = value
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun <T> rtta(value: T, key: String): LogAttr {
    return put(key, value)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun <T>         x(value: T): LogAttr = rtta(value, "x")
  fun <T>        dx(value: T): LogAttr = rtta(value, "dx")
  fun <T>      xext(value: T): LogAttr = rtta(value, "xext")    // x-extent (width)
  fun <T>         y(value: T): LogAttr = rtta(value, "y")
  fun <T>        dy(value: T): LogAttr = rtta(value, "dy")
  fun <T>      yext(value: T): LogAttr = rtta(value, "yext")    // y-extent (height)
  fun <T>         z(value: T): LogAttr = rtta(value, "z")
  fun <T>        dz(value: T): LogAttr = rtta(value, "dz")
  fun <T>      zext(value: T): LogAttr = rtta(value, "zext")    // z-extent
  fun <T>         w(value: T): LogAttr = rtta(value, "w")
  fun <T>        dw(value: T): LogAttr = rtta(value, "dw")
  fun <T>      wext(value: T): LogAttr = rtta(value, "wext")    // w-extent

  // 2nd version (i.e. before/after)
  fun <T>        x2(value: T): LogAttr = rtta(value, "x2")
  fun <T>       dx2(value: T): LogAttr = rtta(value, "dx2")
  fun <T>        y2(value: T): LogAttr = rtta(value, "y2")
  fun <T>       dy2(value: T): LogAttr = rtta(value, "dy2")
  fun <T>        z2(value: T): LogAttr = rtta(value, "z2")
  fun <T>       dz2(value: T): LogAttr = rtta(value, "dz2")
  fun <T>        w2(value: T): LogAttr = rtta(value, "w2")
  fun <T>       dw2(value: T): LogAttr = rtta(value, "dw2")

  fun <T>       len(value: T): LogAttr = rtta(value, "len")
  fun <T>     width(value: T): LogAttr = rtta(value, "width")
  fun <T>    height(value: T): LogAttr = rtta(value, "height")
  fun <T>      size(value: T): LogAttr = rtta(value, "size")

  // ------------------------------------------------------------------------------------------------------------------
  fun partition() {
    attr("partn", true).end()
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun end() {
    if (Constants.SEND_ALL_MODE) {
      truss.sendLogAttr(this)
      return
    }
    truss.addLogAttr(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  fun send() {
    truss.sendLogAttr(this)
  }

  // ------------------------------------------------------------------------------------------------------------------
  override fun toString(): String {
    return data.toValue("ent", theEnt)
  }



  // ==================================================================================================================
  // Internal
  // ------------------------------------------------------------------------------------------------------------------
  private fun <T> put(key: String, value: T): LogAttr {
    data.putIt(key, value)
    return this
  }

  // ------------------------------------------------------------------------------------------------------------------
  private fun smartPut(key: String, value: String): LogAttr {
    data.smartPutIt(key, value)
    return this
  }

}
