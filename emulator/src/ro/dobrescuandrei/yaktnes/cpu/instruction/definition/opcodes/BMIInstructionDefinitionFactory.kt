package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.bmi
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object BMIInstructionDefinitionFactory : InstructionGroupDefinitionFactory<ProgramCounterDelta>
{
    override fun newInstance() : InstructionGroupDefinition<ProgramCounterDelta>
    {
        return InstructionGroupDefinition(
            name = "BMI",
            argumentType = ProgramCounterDelta::class.java,
            execution = ::bmi,
            definitions = listOf(
                InstructionDefinition(
                    id = 0x30.toByte(),
                    addressingMode = AddressingMode.Relative
                )
            )
        )
    }
}
