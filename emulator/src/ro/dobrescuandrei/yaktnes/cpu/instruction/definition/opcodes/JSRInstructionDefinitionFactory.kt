package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.jsr

object JSRInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer.ToMachineCode>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer.ToMachineCode>
    {
        return InstructionGroupDefinition(
            name = "JSR",
            argumentType = Pointer.ToMachineCode::class.java,
            execution = ::jsr,
            definitions = listOf(
                InstructionDefinition(
                    id = 0x20,
                    addressingMode = AddressingMode.Absolute
                )
            )
        )
    }
}
