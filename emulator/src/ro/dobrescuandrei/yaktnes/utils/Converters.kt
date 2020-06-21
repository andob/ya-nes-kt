package ro.dobrescuandrei.yaktnes.utils

fun Boolean.toInt() = if (this) 1 else 0

fun Byte.toIntArrayWithBits() = IntArray(size = 8, init = { i ->
    this.toInt().shr(i).and(0x01)
})
