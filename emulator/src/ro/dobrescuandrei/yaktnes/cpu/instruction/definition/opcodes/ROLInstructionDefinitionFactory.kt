package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.rol

object ROLInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "ROL",
            argumentType = Pointer::class.java,
            execution = ::rol,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x2A,
                    addressingMode = AddressingMode.Accumulator
                ),

                InstructionDefinition(
                    id = 0x26,
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0x36,
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0x2E,
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0x3E,
                    addressingMode = AddressingMode.AbsoluteX
                )
            )
        )
    }

}
