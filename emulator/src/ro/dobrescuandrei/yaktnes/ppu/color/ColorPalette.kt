package ro.dobrescuandrei.yaktnes.ppu.color

import com.badlogic.gdx.graphics.Color

class ColorPalette
(
    val colors : Array<Color>,
    val universalBackgroundColor : Color
)
{
    init
    {
        if (colors.size!=3)
            throw RuntimeException("Color pallette must have 3 colors!")
    }
}
