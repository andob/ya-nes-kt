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

data class SizeF
(
    val width : Float,
    val height : Float
)
{
    override fun equals(other : Any?) =
        (other as? SizeF)?.width==width&&
        (other as? SizeF)?.height==height

    override fun hashCode() = (width*31+height).toInt()
}

data class PointF
(
    val x : Float,
    val y : Float
)
{
    override fun equals(other : Any?) =
        (other as? PointF)?.x==x&&
        (other as? PointF)?.y==y

    override fun hashCode() = (x*31+y).toInt()
}
