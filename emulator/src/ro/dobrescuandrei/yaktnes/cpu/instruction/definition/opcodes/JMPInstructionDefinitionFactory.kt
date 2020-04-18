package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.jmp

object JMPInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer.ToMachineCode>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer.ToMachineCode>
    {
        return InstructionGroupDefinition(
            name = "JMP",
            argumentType = Pointer.ToMachineCode::class.java,
            execution = ::jmp,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x4C,
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0x6C,
                    addressingMode = AddressingMode.Indirect
                )
            )
        )
    }
}
