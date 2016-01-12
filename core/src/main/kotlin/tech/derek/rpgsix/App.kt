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
        val mapSize = loadMap("tiled.tmx", visibleEntities)

        // Yes I know this is terrible, it's late at night and I'm tired.
        renderer.cam.translate(renderer.cam.position.cpy().scl(-1f))
        renderer.cam.translate(Vector3(mapSize.x.toFloat() / 2f, 18f, mapSize.y.toFloat()));
        renderer.cam.lookAt(Vector3(mapSize.x.toFloat() / 2f, 0f, mapSize.y.toFloat() / 2f))
        renderer.cam.rotate(Vector3.Y, 180f)
        renderer.cam.translate(Vector3(0f, 0f, -mapSize.y.toFloat()))
        renderer.cam.update();
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
