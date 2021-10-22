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
  }

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
