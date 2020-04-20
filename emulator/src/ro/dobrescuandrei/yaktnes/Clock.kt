package ro.dobrescuandrei.yaktnes

class Clock
(
    val speedInNanoseconds : Int
)
{
    companion object
    {
        fun withSpeedInHertz(speedInHertz : Int) : Clock
        {
            //s = 1/Hz, ms = 10^3/Hz, microsecond = 10^6/Hz, ns = 10^9/Hz
            val speedInNanoseconds=1000000000/speedInHertz
            return Clock(speedInNanoseconds)
        }

        fun withSpeedInKiloHertz(speedInKHz : Float) : Clock
        {
            //1.66 MHz = 1660 kHz = 1660000 Hz
            val speedInHertz=(speedInKHz*1000).toInt()
            return Clock.withSpeedInHertz(speedInHertz)
        }

        fun withSpeedInMegaHertz(speedInMHz : Float) : Clock
        {
            //1.66 MHz = 1660 kHz = 1660000 Hz
            val speedInKiloHertz=speedInMHz*1000
            return Clock.withSpeedInKiloHertz(speedInKiloHertz)
        }
    }

    fun await(deltaTimeInNs : Long)
    {
        val startTimestampInNs=System.nanoTime()
        while (System.nanoTime()-startTimestampInNs<deltaTimeInNs) {}
    }
}
