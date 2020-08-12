package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.graphics.Color
import org.reflections.Reflections
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.ppu.color.ColorPalettes
import ro.dobrescuandrei.yaktnes.ppu.drawable.Frame
import ro.dobrescuandrei.yaktnes.ppu.nametable.Nametables
import ro.dobrescuandrei.yaktnes.ppu.register.*
import ro.dobrescuandrei.yaktnes.ppu.scanline.ScanlinesManager
import ro.dobrescuandrei.yaktnes.utils.Clock

//Picture Processing Unit
class PPU
{
    val currentFrame = Frame()

    var clock = Clock.withSpeedInMegaHertz(5.31f)

    val scanlinesManager = ScanlinesManager()

    val colorPalettes = ColorPalettes()

    val nametables = Nametables()

    val registersMap by lazy {
        Reflections(this::class.java.`package`.name)
            .getSubTypesOf(PPURegister::class.java)
            .map { registerClass -> registerClass.newInstance()!! }
            .associateBy { it::class.java }
    }

    inline fun <reified T : PPURegister> getRegister() = registersMap[T::class.java] as T

    operator fun set(pointer : Pointer, value : Int8)
    {
        for (register in registersMap.values)
            if (register.addressOnCpuBus==pointer)
                register.setValue(value)

        getRegister<StatusRegister>().lastValueWroteIntoARegister=value
    }

    operator fun get(pointer : Pointer) : Int8
    {
        for (register in registersMap.values)
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
