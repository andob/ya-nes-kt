package ro.dobrescuandrei.yaktnes.cpu.instruction

import ro.dobrescuandrei.yaktnes.cpu.CPU
import ro.dobrescuandrei.yaktnes.cpu.CpuBus
import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer

fun lda(value : Decimal)
{
    CPU.A = value

    CPU.status = CPU.Status(
        N = value<0,
        Z = value.isNil())
}

fun sta(address : Pointer)
{
    CpuBus[address] = CPU.A
}
