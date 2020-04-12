package ro.dobrescuandrei.yaktnes.cpu.instruction.execution

import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy.InstructionExecutionStrategy
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy.InstructionWithInt8ArgumentExecutionStrategy
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy.InstructionWithPointerArgumentExecutionStrategy
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy.InstructionWithoutArgumentExecutionStrategy
import ro.dobrescuandrei.yaktnes.cpu.MachineCode

object InstructionExecutor
{
    private val strategies = listOf(
        InstructionWithoutArgumentExecutionStrategy(),
        InstructionWithInt8ArgumentExecutionStrategy(),
        InstructionWithPointerArgumentExecutionStrategy()
    ) as List<InstructionExecutionStrategy<*>>

    fun executeInstruction(definition : InstructionDefinition<Any>, machineCode : MachineCode)
    {
        strategies.find { strategy ->
            strategy.getInstructionArgumentType()==definition.groupDefinition.argumentType
        }?.let { strategy ->
            strategy as InstructionExecutionStrategy<Any>
            strategy.executeInstruction(definition, machineCode)
        }
    }
}
