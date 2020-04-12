package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.InstructionArgumentFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.InstructionExecutor
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

object CPU
{
    var A  = Decimal(0x00)  //the A (Accumulator) register
    var X  = Decimal(0x00)  //the X register
    var Y  = Decimal(0x00)  //the Y register

    var programCounter : Decimal = Decimal(0x00)

    var status = Status()

    data class Status
    (
        val C : Boolean = false, //carry bit
        val Z : Boolean = false, //zero?
        val I : Boolean = false, //disable interrupts
        val D : Boolean = false, //decimal mode
        val B : Boolean = false, //break
        val U : Boolean = false, //unused
        val V : Boolean = false, //overflow
        val N : Boolean = false  //negative
    )

    fun execute(machineCode : MachineCode)
    {
        while (machineCode.hasNext())
        {
            InstructionDefinitions[machineCode.nextByte()]?.let { definition ->
                InstructionExecutor.executeInstruction(definition, machineCode)
            }
        }
    }
}
