package ro.dobrescuandrei.yaktnes.ppu.scanline

import ro.dobrescuandrei.yaktnes.utils.Point

class Cycle
(
    val index : Int,
    val parentScanline : Scanline
)
{
    companion object
    {
        val Zero = Cycle(index = 0, parentScanline = Scanline.Zero)
        val MaxValue = Cycle(index = 340, parentScanline = Scanline.MaxValue)
    }

    fun isInVisibleArea() =
        index<ScreenResolution.width&&
        parentScanline.isInVisibleArea()

    fun isInVerticalBlankPeriod() =
        parentScanline.isInVerticalBlankPeriod()

    operator fun inc() = Cycle(index = index+1, parentScanline = parentScanline)

    override fun hashCode() = index*31+parentScanline.hashCode()
    override fun equals(other : Any?) =
        (other as? Cycle)?.index==index&&
        other.parentScanline==parentScanline

    override fun toString() = index.toString()

    fun toPoint() = Point(x = index, y = parentScanline.index)

    fun toLocationOnScreen() =
        LocationOnScreen(scanline = parentScanline, cycle = this, point = toPoint())
}
