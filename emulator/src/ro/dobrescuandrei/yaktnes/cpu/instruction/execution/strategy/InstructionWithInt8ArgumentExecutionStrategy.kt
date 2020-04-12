package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.InstructionArgumentFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.MachineCode

class InstructionWithInt8ArgumentExecutionStrategy : InstructionExecutionStrategy<Int8>
{
    override fun getInstructionArgumentType() = Int8::class.java

    override fun executeInstruction(definition : InstructionDefinition<Int8>, machineCode : MachineCode)
    {
        val value=InstructionArgumentFactory.getInt8Argument(
                addressingMode = definition.addressingMode,
                machineCode = machineCode)

        definition.groupDefinition.execution.invoke(value)
    }
}
