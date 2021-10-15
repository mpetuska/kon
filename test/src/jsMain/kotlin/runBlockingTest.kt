package local.test

import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.promise

actual typealias CoroutineScope = CoroutineScope

actual fun runBlockingTest(test: suspend CoroutineScope.() -> Unit): dynamic =
  CoroutineScope(EmptyCoroutineContext).promise(block = test)
