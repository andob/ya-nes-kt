package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.utils.Clock
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions
import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.InstructionExecutor
import ro.dobrescuandrei.yaktnes.utils.toInt

class CPU
{
    var A = Int8.Zero  //the A (Accumulator) register
    var X = Int8.Zero  //the X register
    var Y = Int8.Zero  //the Y register

    var programCounter = Pointer.ToMachineCode.Zero

    var clock = Clock.withSpeedInMegaHertz(1.77f)

    val stack = CPUStack()

    var status = Status()

    data class Status
    (
        var N : Boolean = false, //was last result negative?
        var V : Boolean = false, //last result caused an overflow?
        var U : Boolean = true,  //unused
        var B : Boolean = true,  //unused
        var D : Boolean = false, //is decimal mode enabled?
        var I : Boolean = false, //should disable interrupts?
        var Z : Boolean = false, //was last result zero?
        var C : Boolean = false  //is carry mode enabled?
    )
    {
        fun toByte() =
            0x00.or(N.toInt()).shl(1)
                .or(V.toInt()).shl(1)
                .or(U.toInt()).shl(1)
                .or(B.toInt()).shl(1)
                .or(D.toInt()).shl(1)
                .or(I.toInt()).shl(1)
                .or(Z.toInt()).shl(1)
                .or(C.toInt()).toByte()

        companion object
        {
            @JvmStatic
            fun fromByte(byte : Byte) = Status(
                N = byte.toInt().shr(7).and(1)>0,
                V = byte.toInt().shr(6).and(1)>0,
                U = byte.toInt().shr(5).and(1)>0,
                B = byte.toInt().shr(4).and(1)>0,
                D = byte.toInt().shr(3).and(1)>0,
                I = byte.toInt().shr(2).and(1)>0,
                Z = byte.toInt().shr(1).and(1)>0,
                C = byte.toInt().and(1)>0)
        }
    }

    fun execute(machineCode : MachineCode)
    {
        while (machineCode.hasNextByte())
        {
            InstructionDefinitions[machineCode.nextByte()]?.let { definition ->
                val startTimeInNs=System.nanoTime()

                InstructionExecutor.executeInstruction(definition, machineCode)

                val expectedExecutionTimeInNs=definition.targetExecutionTime*clock.speedInNanoseconds
                val actualExecutionTimeInNs=System.nanoTime()-startTimeInNs
                if (actualExecutionTimeInNs>=expectedExecutionTimeInNs)
                {
                    //todo enable this warning, benchmark and optimize execution!!!
//                    System.err.println("WARNING!!! INTRUCTION TOOK TOO MUCH TIME TO RUN! ${definition.groupDefinition.name}")
                }
                else clock.await(deltaTimeInNs = expectedExecutionTimeInNs-actualExecutionTimeInNs)
            }
        }
    }
}
