package com.nmrc.datastructure.util

import com.nmrc.core.Structure

fun <E>Structure<E>.toList(): List<E> {
    val data = ArrayList<E>()

    this.forEach {
        data.add(it)
    }
    return data
}