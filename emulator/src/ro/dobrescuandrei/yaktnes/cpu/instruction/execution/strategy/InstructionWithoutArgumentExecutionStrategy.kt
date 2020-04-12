package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

class InstructionWithoutArgumentExecutionStrategy : InstructionExecutionStrategy<Unit>
{
    override fun getInstructionArgumentType() = Unit::class.java

    override fun executeInstruction(definition : InstructionDefinition<Unit>, machineCode : MachineCode)
    {
        definition.groupDefinition.execution.invoke(Unit)
    }
}
