package ro.dobrescuandrei.yaktnes.ppu.scanline

import ro.dobrescuandrei.yaktnes.utils.Point

class LocationOnScreen
(
    val scanline : Scanline,
    val cycle : Cycle,
    val point : Point
)
{
    override fun hashCode() = point.hashCode()
    override fun equals(other : Any?) =
        (other as? LocationOnScreen)?.scanline==scanline&&
        other.cycle==cycle&&other.point==point
}
