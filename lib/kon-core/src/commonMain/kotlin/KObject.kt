package dev.petuska.kon

import dev.petuska.kon.util.toJson

/** A [MutableMap] to represent a JSON object. */
public typealias KON = MutableMap<String, Any?>

/** A wrapper around [MutableMap] to represent a JSON object */
public interface KObject : KON {
  /**
   * Adds a field
   * @receiver field name
   * @param value field value
   */
  @KONSetterDsl
  public infix fun String.to(value: Any?) {
    this@KObject[this] = value
  }

  /**
   * Adds an array field
   * @receiver field name
   * @param items array value items
   */
  @KONSetterDsl
  public operator fun String.get(vararg items: Any?) {
    this@KObject[this] = karr(*items)
  }

  /**
   * Adds an object field
   * @receiver field name
   * @param obj object builder
   */
  @KONSetterDsl
  public operator fun String.invoke(obj: KObject.() -> Unit) {
    this@KObject[this] = kobj(obj = obj)
  }

  /** Array builder hook. Useless by its own... */
  public object ARRAY {
    /**
     * Builds an array
     * @param items array value items
     * @return built array
     */
    @KONBuilderDsl public operator fun <T> get(vararg items: T): KArray<T> = karr(*items)
  }

  /** Array builder hook. Useless by its own... */
  @KONBuilderDsl
  public val karr: ARRAY
    get() = ARRAY
}

/**
 * Builds an object. Overrides [Any::toString] to return JSON object notation.
 * @param obj object builder
 */
@KONBuilderDsl
public inline fun kobj(base: KON = mutableMapOf(), obj: KObject.() -> Unit): KObject =
  object : KObject, KON by base {
      override fun toString(): String = toJson()
    }
    .apply(obj)
