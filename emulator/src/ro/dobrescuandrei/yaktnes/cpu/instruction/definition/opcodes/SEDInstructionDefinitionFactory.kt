package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.sed

object SEDInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Unit>
{
    override fun newInstance() : InstructionGroupDefinition<Unit>
    {
        return InstructionGroupDefinition(
            name = "SED",
            argumentType = Unit::class.java,
            execution = { sed() },
            definitions = listOf(
                InstructionDefinition(
                    id = 0xF8.toByte(),
                    addressingMode = AddressingMode.Implicit,
                    targetExecutionTime = 2
                )
            )
        )
    }
}
