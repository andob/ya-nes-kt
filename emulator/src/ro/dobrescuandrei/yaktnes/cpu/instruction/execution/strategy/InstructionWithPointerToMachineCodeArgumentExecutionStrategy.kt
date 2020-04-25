package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.InstructionArgumentFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition

class InstructionWithPointerToMachineCodeArgumentExecutionStrategy : InstructionExecutionStrategy<Pointer.ToMachineCode>
{
    override fun getInstructionArgumentType() = Pointer.ToMachineCode::class.java

    override fun executeInstruction(definition : InstructionDefinition<Pointer.ToMachineCode>, machineCode : MachineCode)
    {
        val pointer=InstructionArgumentFactory.getPointerArgument(
                addressingMode = definition.addressingMode,
                machineCode = machineCode)

        val pointerToMachineCode=Pointer.ToMachineCode(pointer.toUShort())

        println(definition.groupDefinition.name+" "+pointerToMachineCode)

        definition.groupDefinition.execution.invoke(pointerToMachineCode)
    }
}
