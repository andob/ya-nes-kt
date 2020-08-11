package ro.dobrescuandrei.yaktnes.ppu.scanline

class Scanline
(
    val index : Int
)
{
    companion object
    {
        val Zero = Scanline(index = 0)
        val MaxValue = Scanline(index = 261)
    }

    fun isInVisibleArea() =
        index<ScreenResolution.height

    fun isInVerticalBlankPeriod() =
        index>ScreenResolution.height

    operator fun inc() = Scanline(index = index+1)

    override fun hashCode() = index
    override fun equals(other : Any?) = (other as? Scanline)?.index==index

    override fun toString() = index.toString()

    fun toCycle(index : Int) = Cycle(index, parentScanline = this)
}
