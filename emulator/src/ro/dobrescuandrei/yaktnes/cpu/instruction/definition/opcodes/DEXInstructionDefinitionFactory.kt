package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.dex

object DEXInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Unit>
{
    override fun newInstance() : InstructionGroupDefinition<Unit>
    {
        return InstructionGroupDefinition(
            name = "DEX",
            argumentType = Unit::class.java,
            execution = { dex() },
            definitions = listOf(
                InstructionDefinition(
                    id = 0xCA.toByte(),
                    addressingMode = AddressingMode.Implicit,
                    targetExecutionTime = 2
                )
            )
        )
    }
}
