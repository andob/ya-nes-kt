package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.cpx
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object CPXInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "CPX",
            argumentType = Int8::class.java,
            execution = ::cpx,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xE0.toByte(),
                    addressingMode = AddressingMode.Immediate
                ),

                InstructionDefinition(
                    id = 0xE4.toByte(),
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0xEC.toByte(),
                    addressingMode = AddressingMode.Absolute
                )
            )
        )
    }
}
