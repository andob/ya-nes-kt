package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.InstructionExecutor

class CPU
{
    var A  = Int8(0x00)  //the A (Accumulator) register
    var X  = Int8(0x00)  //the X register
    var Y  = Int8(0x00)  //the Y register

    var programCounter = 0

    var status = Status()

    data class Status
    (
        val C : Boolean = false, //is carry mode enabled?
        val Z : Boolean = false, //was last result zero?
        val I : Boolean = false, //should disable interrupts?
        val B : Boolean = false, //should break?
        val V : Boolean = false, //last result caused an overflow?
        val N : Boolean = false  //was last result negative?
    )

    fun execute(machineCode : MachineCode)
    {
        while (machineCode.hasNextByte())
        {
            InstructionDefinitions[machineCode.nextByte()]?.let { definition ->
                InstructionExecutor.executeInstruction(definition, machineCode)
            }
        }
    }
}
