package tech.derek.rpgsix

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import tech.derek.rpgsix.entites.Block
import com.badlogic.gdx.utils.Array
import tech.derek.rpgsix.modifiers.VisibleModel

class App : ApplicationAdapter()
{
    val visibleEntities = Array<VisibleModel>()
    lateinit var renderer: Renderer

    override fun create()
    {
        renderer = Renderer(70f);
        loadMap("tiled.tmx", visibleEntities)

    }

    override fun render()
    {
        renderer.render(visibleEntities)
    }


    override fun resize (width: Int, height: Int)
    {
        renderer.resize(width, height)
    }


    override fun dispose()
    {
        super.dispose()
    }
}
