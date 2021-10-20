package dev.petuska.kon

import dev.petuska.kon.util.toJson

/** A [MutableList] to represent a JSON object. */
public interface KArray<T> : MutableList<T>

/**
 * Builds an array. Overrides [Any::toString] to return JSON array.
 * @param items array value items
 */
@KONBuilderDsl
@Suppress("NOTHING_TO_INLINE")
public inline fun <T> karr(vararg items: T): KArray<T> =
    object : KArray<T>, MutableList<T> by items.toMutableList() {
      override fun toString(): String = toJson()
    }

/** Array builder hook. Useless by its own... */
public object KARR {
  /**
   * Builds an array
   * @param items array value items
   * @return built array
   */
  @KONBuilderDsl
  public operator fun <T> get(vararg items: T): KArray<T> {
    return karr(items = items)
  }
}

/** Array builder hook. Useless by its own... */
@KONBuilderDsl
public val karr: KARR
  get() = KARR
