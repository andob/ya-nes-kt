package ro.dobrescuandrei.yaktnes.ppu.color

import com.badlogic.gdx.graphics.Color
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer

//todo unit test this
class ColorPalettes
(
    private val colors : Array<Color> =
        Array(size = 32, init = { ColorFactory.newColor() })
)
{
    companion object
    {
        val START_ADDRESS = 0x3F00.toUShort()
    }

    private fun Pointer.toIndex() = (toUShort()-START_ADDRESS).toInt()

    operator fun get(pointer : Pointer) : Color
    {
        return colors[pointer.toIndex()]
    }

    operator fun set(pointer : Pointer, color : Color)
    {
        colors[pointer.toIndex()]=color

        if (pointer.toIndex().rem(4)==0)
        {
            //mirroring universal background color
            for (i in 0 until colors.size step 4)
                colors[i]=color
        }
    }

    fun getUniversalBackgroundColor() = colors.first()

    fun getBackgroundPalettes() = (0 until 4).map { index ->
        ColorPalette(universalBackgroundColor = getUniversalBackgroundColor(),
            colors = colors.drop(n=1+4*index).take(n=3).toTypedArray())
    }

    fun getSpritePalettes() = (0 until 4).map { index ->
        ColorPalette(universalBackgroundColor = getUniversalBackgroundColor(),
            colors = colors.drop(n=17+4*index).take(n=3).toTypedArray())
    }
}
