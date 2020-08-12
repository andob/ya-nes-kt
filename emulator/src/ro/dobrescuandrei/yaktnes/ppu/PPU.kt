package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.graphics.Color
import org.reflections.Reflections
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.ppu.color.ColorPalettes
import ro.dobrescuandrei.yaktnes.ppu.register.*
import ro.dobrescuandrei.yaktnes.ppu.scanline.ScanlinesManager
import ro.dobrescuandrei.yaktnes.utils.Clock

//Picture Processing Unit
class PPU
{
    var clock = Clock.withSpeedInMegaHertz(5.31f)

    val colorPalettes = ColorPalettes()

    val scanlinesManager = ScanlinesManager()

    val currentFrame = Frame()

    val registers by lazy {
        Reflections(this::class.java.`package`.name)
            .getSubTypesOf(PPURegister::class.java)
            .map { registerClass -> registerClass.newInstance()!! }
    }

    inline fun <reified T> getRegister() =
        registers.find { it::class.java==T::class.java }!! as T

    operator fun set(pointer : Pointer, value : Int8)
    {
        for (register in registers)
            if (register.addressOnCpuBus==pointer)
                register.setValue(value)

        getRegister<StatusRegister>().lastValueWroteIntoARegister=value
    }

    operator fun get(pointer : Pointer) : Int8
    {
        for (register in registers)
            if (register.addressOnCpuBus==pointer)
                return register.getValue()
        return Int8.Zero
    }

    fun render()
    {
        while (true)
        {
            val locationOnScreen=scanlinesManager.nextLocationOnScreen()

            getRegister<StatusRegister>().wasVerticalBlankPeriodStarted=
                    locationOnScreen.scanline.isInVerticalBlankPeriod()

            currentFrame[locationOnScreen]=Color.RED

            //todo tune the clock
            //clock.await(1)
        }
    }
}
