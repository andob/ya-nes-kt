package ro.dobrescuandrei.yaktnes

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import ro.dobrescuandrei.yaktnes.ppu.Renderer

class AndroidLauncher : AndroidApplication()
{
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        val config=AndroidApplicationConfiguration()
        initialize(Renderer(), config)
    }
}
