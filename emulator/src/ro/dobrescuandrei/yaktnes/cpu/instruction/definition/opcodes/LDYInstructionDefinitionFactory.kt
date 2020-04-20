package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.ldy

object LDYInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "LDY",
            argumentType = Int8::class.java,
            execution = ::ldy,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xA0.toByte(),
                    addressingMode = AddressingMode.Immediate,
                    targetExecutionTime = 2
                ),

                InstructionDefinition(
                    id = 0xA4.toByte(),
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 3
                ),

                InstructionDefinition(
                    id = 0xB4.toByte(),
                    addressingMode = AddressingMode.ZeroPageX,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0xAC.toByte(),
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0xBC.toByte(),
                    addressingMode = AddressingMode.AbsoluteX,
                    targetExecutionTime = 4
                )
            )
        )
    }
}
