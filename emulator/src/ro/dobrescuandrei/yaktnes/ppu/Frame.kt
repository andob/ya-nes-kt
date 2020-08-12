package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.graphics.Color
import ro.dobrescuandrei.yaktnes.ppu.scanline.LocationOnScreen
import ro.dobrescuandrei.yaktnes.ppu.scanline.ScreenResolution

class Frame
{
    private val pixels = Array(
        size = ScreenResolution.width,
        init = { Array(
            size = ScreenResolution.height,
            init = { Color.BLACK!! })
        })

    operator fun set(locationOnScreen : LocationOnScreen, color : Color)
    {
        if (locationOnScreen.point.x>=0&&locationOnScreen.point.x<ScreenResolution.width&&
            locationOnScreen.point.y>=0&&locationOnScreen.point.y<ScreenResolution.height)
            pixels[locationOnScreen.point.x][locationOnScreen.point.y]=color
    }

    fun iterate(consumer : (x : Int, y : Int, color : Color) -> (Unit))
    {
        for (x in pixels.indices)
            for (y in pixels[x].indices)
                consumer.invoke(x, y, pixels[x][y])
    }
}
