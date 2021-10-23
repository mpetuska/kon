package dev.petuska.kon.core

/** Marks DSLs with side effects that build and sets entities */
@DslMarker @Retention(AnnotationRetention.SOURCE) internal annotation class KONSetterDsl

/** Marks DSLs with no side effects that build entities */
@DslMarker @Retention(AnnotationRetention.SOURCE) internal annotation class KONBuilderDsl
