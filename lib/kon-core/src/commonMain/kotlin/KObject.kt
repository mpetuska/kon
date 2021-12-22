package dev.petuska.kon.core

import dev.petuska.kon.core.util.toJson

/** A [MutableMap] to represent a JSON object. */
public typealias KON = TypedKON<Any?>

/** A wrapper around [MutableMap] to represent a JSON object */
public interface KObject : TypedKObject<Any?>, KON {
  /**
   * Adds an object field
   * @receiver field name
   * @param obj object builder
   */
  @KONSetterDsl
  public infix fun String.to(obj: KObject.() -> Unit) {
    this to kobj(obj = obj)
  }

  /**
   * Adds an object field
   * @receiver field name
   * @param obj object builder
   */
  @KONSetterDsl
  public operator fun String.invoke(obj: KObject.() -> Unit) {
    this to obj
  }

  /**
   * Adds an array field
   *
   * NOTE: This will not work when declaring an array with a single integer inside. For those cases
   * use invoke function `"key".to[1]`
   * @receiver field name
   * @param items array value items
   */
  @KONSetterDsl
  public operator fun String.get(vararg items: Any?) {
    this to karr(items = items)
  }

  public class TO(private val map: KObject, private val key: String) {
    /**
     * Adds an array field
     * @param items array value items
     */
    @KONBuilderDsl
    public operator fun <T> get(vararg items: T) {
      map[key] = karr(items = items)
    }

    /**
     * Adds an array field
     * @param items array value items
     */
    @KONBuilderDsl
    public operator fun <T> invoke(vararg items: T) {
      map[key] = karr(items = items)
    }
  }

/** Array builder hook. Useless by its own... */
  @KONBuilderDsl
  public val karr: KARR
    get() = KARR

  /** Array builder hook. Useless by its own... */
  @KONBuilderDsl
  public val String.to: TO
    get() = TO(this@KObject, this)
}

/**
 * Builds an object. Overrides [Any::toString] to return JSON object notation.
 * @param obj object builder
 */
@KONBuilderDsl
public inline fun kobj(base: KON = mutableMapOf(), obj: KObject.() -> Unit = {}): KObject =
  object : KObject, KON by base {
    override fun toString(): String = toJson()
  }
    .apply(obj)
