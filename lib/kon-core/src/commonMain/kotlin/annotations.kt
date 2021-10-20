package dev.petuska.kon

/** Marks DSLs with side effects that build and sets entities */
@DslMarker @Retention(AnnotationRetention.SOURCE) public annotation class KONSetterDsl

/** Marks DSLs with no side effects that build entities */
@DslMarker @Retention(AnnotationRetention.SOURCE) public annotation class KONBuilderDsl
