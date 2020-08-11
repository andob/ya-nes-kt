package ro.dobrescuandrei.yaktnes.utils

data class Size
(
    val width : Int,
    val height : Int
)
{
    override fun equals(other : Any?) =
        (other as? Size)?.width==width&&
        (other as? Size)?.height==height

    override fun hashCode() = width*31+height
}

data class Point
(
    val x : Int,
    val y : Int
)
{
    override fun equals(other : Any?) =
        (other as? Point)?.x==x&&
        (other as? Point)?.y==y

    override fun hashCode() = x*31+y
}

typealias Vector = Point
