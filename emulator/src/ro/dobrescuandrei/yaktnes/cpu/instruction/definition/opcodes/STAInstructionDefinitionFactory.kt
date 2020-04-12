package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.sta

object STAInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "STA",
            argumentType = Pointer::class.java,
            execution = ::sta,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x85.toByte(),
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0x95.toByte(),
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0x8D.toByte(),
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0x9D.toByte(),
                    addressingMode = AddressingMode.AbsoluteX
                ),

                InstructionDefinition(
                    id = 0x99.toByte(),
                    addressingMode = AddressingMode.AbsoluteY
                ),

                InstructionDefinition(
                    id = 0x81.toByte(),
                    addressingMode = AddressingMode.IndirectX
                ),

                InstructionDefinition(
                    id = 0x92.toByte(),
                    addressingMode = AddressingMode.IndirectY
                )
            )
        )
    }
}
