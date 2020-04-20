package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.tsx

object TSXInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Unit>
{
    override fun newInstance() : InstructionGroupDefinition<Unit>
    {
        return InstructionGroupDefinition(
            name = "TSX",
            argumentType = Unit::class.java,
            execution = { tsx() },
            definitions = listOf(
                InstructionDefinition(
                    id = 0xBA.toByte(),
                    addressingMode = AddressingMode.Implicit,
                    targetExecutionTime = 2
                )
            )
        )
    }
}

