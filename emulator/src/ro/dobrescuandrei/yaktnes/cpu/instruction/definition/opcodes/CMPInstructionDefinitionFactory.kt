package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.cmp
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object CMPInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "CMP",
            argumentType = Int8::class.java,
            execution = ::cmp,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xC9.toByte(),
                    addressingMode = AddressingMode.Immediate
                ),

                InstructionDefinition(
                    id = 0xC5.toByte(),
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0xD5.toByte(),
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0xCD.toByte(),
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0xDD.toByte(),
                    addressingMode = AddressingMode.AbsoluteX
                ),

                InstructionDefinition(
                    id = 0xD9.toByte(),
                    addressingMode = AddressingMode.AbsoluteY
                ),

                InstructionDefinition(
                    id = 0xC1.toByte(),
                    addressingMode = AddressingMode.IndirectX
                ),

                InstructionDefinition(
                    id = 0xD1.toByte(),
                    addressingMode = AddressingMode.IndirectY
                )
            )
        )
    }
}
