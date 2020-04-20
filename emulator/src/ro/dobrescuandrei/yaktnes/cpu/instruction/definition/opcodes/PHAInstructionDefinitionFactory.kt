package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.pha

object PHAInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Unit>
{
    override fun newInstance() : InstructionGroupDefinition<Unit>
    {
        return InstructionGroupDefinition(
            name = "PHA",
            argumentType = Unit::class.java,
            execution = { pha() },
            definitions = listOf(
                InstructionDefinition(
                    id = 0x48.toByte(),
                    addressingMode = AddressingMode.Implicit,
                    targetExecutionTime = 3
                )
            )
        )
    }
}
