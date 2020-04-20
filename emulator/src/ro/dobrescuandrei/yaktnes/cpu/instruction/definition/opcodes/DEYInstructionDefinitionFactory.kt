package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.dey

object DEYInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Unit>
{
    override fun newInstance() : InstructionGroupDefinition<Unit>
    {
        return InstructionGroupDefinition(
            name = "DEY",
            argumentType = Unit::class.java,
            execution = { dey() },
            definitions = listOf(
                InstructionDefinition(
                    id = 0x88.toByte(),
                    addressingMode = AddressingMode.Implicit,
                    targetExecutionTime = 2
                )
            )
        )
    }
}
