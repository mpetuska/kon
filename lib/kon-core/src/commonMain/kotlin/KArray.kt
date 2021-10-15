package dev.petuska.kon

import dev.petuska.kon.util.toJson

/**
 * A [MutableList] to represent a JSON object.
 */
public interface KArray<T> : MutableList<T>

/**
 * Builds an array.
 * Overrides [Any::toString] to return JSON array.
 * @param items array value items
 */
@KONBuilderDsl
@Suppress("NOTHING_TO_INLINE")
public inline fun <T> karr(vararg items: T): KArray<T> = object : KArray<T>, MutableList<T> by mutableListOf(*items) {
  override fun toString(): String = toJson()
}
