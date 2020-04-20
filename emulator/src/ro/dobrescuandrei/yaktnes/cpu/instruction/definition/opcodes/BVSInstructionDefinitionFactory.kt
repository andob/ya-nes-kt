package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.bvs
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object BVSInstructionDefinitionFactory : InstructionGroupDefinitionFactory<ProgramCounterDelta>
{
    override fun newInstance() : InstructionGroupDefinition<ProgramCounterDelta>
    {
        return InstructionGroupDefinition(
            name = "BVS",
            argumentType = ProgramCounterDelta::class.java,
            execution = ::bvs,
            definitions = listOf(
                InstructionDefinition(
                    id = 0x70.toByte(),
                    addressingMode = AddressingMode.Relative,
                    targetExecutionTime = 3
                )
            )
        )
    }
}
