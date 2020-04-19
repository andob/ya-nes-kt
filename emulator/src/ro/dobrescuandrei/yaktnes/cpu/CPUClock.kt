package ro.dobrescuandrei.yaktnes.cpu

import java.time.Instant

//todo this is really buggy
class CPUClock
{
    var speedInHertz = 1.66f*1000*1000
    //1.66 MHz = 1660 kHz = 1660000 Hz

    val speedInNanoseconds get() = (1000000000/speedInHertz).toInt()
    //s = 1/Hz, ms = 10^3/Hz, microsecond = 10^6/Hz, ns = 10^9/Hz

    private fun nowInNanoseconds() = Instant.now().nano

    fun executeAndBenchmark(block : () -> (Unit)) : InstructionExecutionBenchmarkResult
    {
        val startTimeInNs=nowInNanoseconds()

        block.invoke()

        val endTimeInNs=nowInNanoseconds()

        return InstructionExecutionBenchmarkResult(
                startTimeInNs = startTimeInNs,
                endTimeInNs = endTimeInNs,
                expectedMaximumTimeInNs = speedInNanoseconds)
    }

    fun await(deltaTimeInNs : Int)
    {
        Thread.sleep(1)
//        val startTimestampInNs=nowInNanoseconds()
//        while (nowInNanoseconds()-startTimestampInNs<deltaTimeInNs) {}
    }
}

class InstructionExecutionBenchmarkResult
(
    val startTimeInNs : Int,
    val endTimeInNs : Int,
    val expectedMaximumTimeInNs : Int
)
{
    val deltaTimeInNs get() = endTimeInNs-startTimeInNs

    fun didExecutionTookTooLong() = deltaTimeInNs>expectedMaximumTimeInNs
}
