package com.stdio.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}