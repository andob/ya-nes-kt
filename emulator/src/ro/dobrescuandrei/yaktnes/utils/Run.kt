package ro.dobrescuandrei.yaktnes.utils

import kotlin.system.exitProcess

object Run
{
    fun thread(block : () -> (Unit))
    {
        Thread {
            try { block() }
            catch (ex : Throwable)
            {
                ex.printStackTrace()
                exitProcess(status = 1)
            }
        }.start()
    }
}
