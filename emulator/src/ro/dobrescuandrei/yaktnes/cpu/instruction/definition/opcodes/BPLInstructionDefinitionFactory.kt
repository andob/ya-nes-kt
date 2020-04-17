package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.bpl
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object BPLInstructionDefinitionFactory : InstructionGroupDefinitionFactory<ProgramCounterDelta>
{
    override fun newInstance() : InstructionGroupDefinition<ProgramCounterDelta>
    {
        return InstructionGroupDefinition(
            name = "BPL",
            argumentType = ProgramCounterDelta::class.java,
            execution = ::bpl,
            definitions = listOf(
                InstructionDefinition(
                    id = 0x10.toByte(),
                    addressingMode = AddressingMode.Relative
                )
            )
        )
    }
}
