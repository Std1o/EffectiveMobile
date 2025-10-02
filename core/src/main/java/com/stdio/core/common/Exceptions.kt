package com.stdio.core.common

import kotlin.reflect.KType

class InvalidArgumentException(expected: String, found: KType?, advice: String = "") :
    RuntimeException(
        "" +
                "\nExpected: $expected" +
                "\nFound: ${found?.classifier}" +
                "\n $advice"
    )