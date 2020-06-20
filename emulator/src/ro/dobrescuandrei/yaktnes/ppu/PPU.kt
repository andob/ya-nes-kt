package ro.dobrescuandrei.yaktnes.ppu

import ro.dobrescuandrei.yaktnes.ppu.color.ColorPalettes

//Picture Processing Unit
class PPU
{
    class Components
    {
        val colorPalettes : ColorPalettes = ColorPalettes()
    }

    val colorPalettes get() = components.colorPalettes

    private var components = Components()
}

