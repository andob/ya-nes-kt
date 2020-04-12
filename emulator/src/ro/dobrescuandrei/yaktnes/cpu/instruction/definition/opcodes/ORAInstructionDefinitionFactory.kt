package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.ora

object ORAInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "ORA",
            argumentType = Int8::class.java,
            execution = ::ora,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x09,
                    addressingMode = AddressingMode.Immediate
                ),

                InstructionDefinition(
                    id = 0x05,
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0x15,
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0x0D,
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0x1D,
                    addressingMode = AddressingMode.AbsoluteX
                ),

                InstructionDefinition(
                    id = 0x19,
                    addressingMode = AddressingMode.AbsoluteY
                ),

                InstructionDefinition(
                    id = 0x01,
                    addressingMode = AddressingMode.IndirectX
                ),

                InstructionDefinition(
                    id = 0x11,
                    addressingMode = AddressingMode.IndirectY
                )
            )
        )
    }
}
