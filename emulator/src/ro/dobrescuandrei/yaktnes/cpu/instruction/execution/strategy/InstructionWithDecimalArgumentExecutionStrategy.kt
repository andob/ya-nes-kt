package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.InstructionArgumentFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

class InstructionWithDecimalArgumentExecutionStrategy : InstructionExecutionStrategy<Decimal>
{
    override fun getInstructionArgumentType() = Decimal::class.java

    override fun executeInstruction(definition : InstructionDefinition<Decimal>, machineCode : MachineCode)
    {
        val value=InstructionArgumentFactory.getDecimalArgument(
                addressingMode = definition.addressingMode,
                machineCode = machineCode)

        definition.groupDefinition.execution.invoke(value)
    }
}
