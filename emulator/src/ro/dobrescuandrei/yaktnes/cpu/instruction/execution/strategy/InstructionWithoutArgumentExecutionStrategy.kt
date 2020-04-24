package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.MachineCode

class InstructionWithoutArgumentExecutionStrategy : InstructionExecutionStrategy<Unit>
{
    override fun getInstructionArgumentType() = Unit::class.java

    override fun executeInstruction(definition : InstructionDefinition<Unit>, machineCode : MachineCode)
    {
        //todo remove this print
        println(definition.groupDefinition.name)

        definition.groupDefinition.execution.invoke(Unit)
    }
}
