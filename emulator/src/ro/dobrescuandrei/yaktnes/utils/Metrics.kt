package ro.dobrescuandrei.yaktnes.utils

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8

data class Size
(
    val width : Int8,
    val height : Int8
)
{
    override fun equals(other : Any?) =
        (other as? Size)?.width==width&&
        (other as? Size)?.height==height

    override fun hashCode() = width.toInt()*31+height.toInt()
}

data class Location
(
    val x : Int8,
    val y : Int8
)
{
    override fun equals(other : Any?) =
        (other as? Location)?.x==x&&
        (other as? Location)?.y==y

    override fun hashCode() = x.toInt()*31+y.toInt()
}

typealias Vector = Location
