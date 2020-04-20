package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.bcc
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object BCCInstructionDefinitionFactory : InstructionGroupDefinitionFactory<ProgramCounterDelta>
{
    override fun newInstance() : InstructionGroupDefinition<ProgramCounterDelta>
    {
        return InstructionGroupDefinition(
            name = "BCC",
            argumentType = ProgramCounterDelta::class.java,
            execution = ::bcc,
            definitions = listOf(
                InstructionDefinition(
                    id = 0x90.toByte(),
                    addressingMode = AddressingMode.Relative,
                    targetExecutionTime = 3
                )
            )
        )
    }
}
