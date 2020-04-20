package ro.dobrescuandrei.yaktnes

import ro.dobrescuandrei.yaktnes.cpu.CPU
import ro.dobrescuandrei.yaktnes.cpu.CPUBus

object NES
{
    class Components
    {
        val CPU : CPU = CPU()
        val CPU_BUS : CPUBus = CPUBus()
    }

    val CPU get() = components.CPU
    val CPU_BUS get() = components.CPU_BUS

    private var components = Components()
    fun reset() { components = Components() }
}
