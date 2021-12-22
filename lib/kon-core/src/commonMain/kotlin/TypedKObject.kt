package dev.petuska.kon.core

import dev.petuska.kon.core.util.toJson

/** A [MutableMap] to represent a JSON object. */
public typealias TypedKON<V> = MutableMap<String, V>

/** A wrapper around [MutableMap] to represent a JSON object */
public interface TypedKObject<V> : TypedKON<V> {
  /**
   * Adds a field
   * @receiver field name
   * @param value field value
   */
  @KONSetterDsl
  public infix fun String.to(value: V) {
    this@TypedKObject[this] = value
  }
}

/**
 * Builds an object. Overrides [Any::toString] to return JSON object notation.
 * @param obj object builder
 */
@KONBuilderDsl
public inline fun <V> kobj(
  base: TypedKON<V> = mutableMapOf(),
  obj: TypedKObject<V>.() -> Unit = {}
): TypedKObject<V> =
  object : TypedKObject<V>, TypedKON<V> by base {
    override fun toString(): String = toJson()
  }
    .apply(obj)
