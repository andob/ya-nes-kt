package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.bvc
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object BVCInstructionDefinitionFactory : InstructionGroupDefinitionFactory<ProgramCounterDelta>
{
    override fun newInstance() : InstructionGroupDefinition<ProgramCounterDelta>
    {
        return InstructionGroupDefinition(
            name = "BVC",
            argumentType = ProgramCounterDelta::class.java,
            execution = ::bvc,
            definitions = listOf(
                InstructionDefinition(
                    id = 0x50.toByte(),
                    addressingMode = AddressingMode.Relative
                )
            )
        )
    }
}
