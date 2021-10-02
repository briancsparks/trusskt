package net.cdr0.trusskt

import kotlin.test.Test
import kotlin.test.assertEquals

class JvmTrussTest {

  @Test
  fun testSanity() {
    val actual = 99 + 1
    assertEquals(100, actual)
  }

}
