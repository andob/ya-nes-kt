package ro.dobrescuandrei.yaktnes.ppu.scanline

import ro.dobrescuandrei.yaktnes.utils.Point

class ScanlinesManager
{
    private var currentScanline = Scanline.Zero
    private var currentCycle = Cycle.Zero

    fun nextPoint() : Point
    {
        val point=currentCycle.toPoint()

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

        return point
    }
}
