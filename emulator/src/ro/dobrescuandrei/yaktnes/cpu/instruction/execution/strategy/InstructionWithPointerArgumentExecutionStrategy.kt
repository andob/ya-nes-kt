package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.InstructionArgumentFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

class InstructionWithPointerArgumentExecutionStrategy : InstructionExecutionStrategy<Pointer>
{
    override fun getInstructionArgumentType() = Pointer::class.java

    override fun executeInstruction(definition : InstructionDefinition<Pointer>, machineCode : MachineCode)
    {
        val pointer=InstructionArgumentFactory.getPointerArgument(
                addressingMode = definition.addressingMode,
                machineCode = machineCode)

        definition.groupDefinition.execution.invoke(pointer)
    }
}
