package local.sandbox

import kotlinx.coroutines.delay
import local.test.runBlockingTest
import org.junit.Test

class MainTest {
  @Test
  fun test() = runBlockingTest {
    println(main())
    delay(500)
    println("After Delay: ${suspendingMain()}")
  }
}
