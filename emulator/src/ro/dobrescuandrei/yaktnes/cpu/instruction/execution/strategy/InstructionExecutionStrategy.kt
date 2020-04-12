package ro.dobrescuandrei.yaktnes.cpu.instruction.execution.strategy

import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

interface InstructionExecutionStrategy<INSTRUCTION_ARGUMENT>
{
    fun getInstructionArgumentType() : Class<INSTRUCTION_ARGUMENT>

    fun executeInstruction(definition : InstructionDefinition<INSTRUCTION_ARGUMENT>, machineCode : MachineCode)
}
