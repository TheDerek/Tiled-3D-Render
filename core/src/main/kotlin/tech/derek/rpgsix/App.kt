package tech.derek.rpgsix

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class App : ApplicationAdapter()
{
    internal lateinit var batch: SpriteBatch
    internal lateinit var img: Texture

    override fun create()
    {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render()
    {
        Gdx.gl.glClearColor(0.5f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(img, 0f, 0f)
        batch.end()
    }
}
