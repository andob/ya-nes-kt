package ro.dobrescuandrei.yaktnes

import org.junit.Assert.assertEquals
import org.junit.Test
import ro.dobrescuandrei.yaktnes.ppu.scanline.Cycle
import ro.dobrescuandrei.yaktnes.ppu.scanline.LocationOnScreen
import ro.dobrescuandrei.yaktnes.ppu.scanline.Scanline
import ro.dobrescuandrei.yaktnes.utils.Point

class PPUScanlinesTest
{
    @Test
    fun testPPUScanlineIteration()
    {
        val points=(0..89351).map { NES.PPU.scanlinesManager.nextLocationOnScreen() }

        val expectedPoints=
            (Scanline.Zero.index..Scanline.MaxValue.index).toList().flatMap { scanlineIndex ->
                (Cycle.Zero.index..Cycle.MaxValue.index).toList().map { cycleIndex ->
                    LocationOnScreen(
                        scanline = Scanline(index = scanlineIndex),
                        cycle = Cycle(index = cycleIndex, parentScanline = Scanline(scanlineIndex)),
                        point = Point(x = cycleIndex, y = scanlineIndex))
                }
            }
            .plus((0..9).map { x ->
                LocationOnScreen(
                    scanline = Scanline.Zero,
                    cycle = Cycle(index = x, parentScanline = Scanline.Zero),
                    point = Point(x = x, y = 0))
            })

        assertEquals(expectedPoints, points)
    }
}
