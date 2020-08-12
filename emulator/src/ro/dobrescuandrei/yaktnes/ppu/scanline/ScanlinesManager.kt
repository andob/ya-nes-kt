package ro.dobrescuandrei.yaktnes.ppu.scanline

class ScanlinesManager
{
    private var currentScanline = Scanline.Zero
    private var currentCycle = Cycle.Zero

    fun nextLocationOnScreen() : LocationOnScreen
    {
        val locationOnScreen=currentCycle.toLocationOnScreen()

        if (currentCycle.index==Cycle.MaxValue.index)
        {
            if (currentScanline.index==Scanline.MaxValue.index)
            {
                currentScanline=Scanline.Zero
                currentCycle=Cycle.Zero
            }
            else
            {
                currentScanline++
                currentCycle=currentScanline.toCycle(index = 0)
            }
        }
        else currentCycle++

        return locationOnScreen
    }
}
