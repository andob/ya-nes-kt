package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.and
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object ANDInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "AND",
            argumentType = Int8::class.java,
            execution = ::and,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x29,
                    addressingMode = AddressingMode.Immediate,
                    targetExecutionTime = 2
                ),

                InstructionDefinition(
                    id = 0x25,
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 3
                ),

                InstructionDefinition(
                    id = 0x35,
                    addressingMode = AddressingMode.ZeroPageX,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x2D,
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x3D,
                    addressingMode = AddressingMode.AbsoluteX,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x39,
                    addressingMode = AddressingMode.AbsoluteY,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x21,
                    addressingMode = AddressingMode.IndirectX,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0x31,
                    addressingMode = AddressingMode.IndirectY,
                    targetExecutionTime = 5
                )
            )
        )
    }
}
