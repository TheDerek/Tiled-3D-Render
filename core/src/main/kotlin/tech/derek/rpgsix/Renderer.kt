package tech.derek.rpgsix

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import tech.derek.rpgsix.modifiers.VisibleModel
import java.util.*


class Renderer(val fov: Float)
{
    var viewport = Vector2(640f, 480f);

    lateinit var cam: PerspectiveCamera
    lateinit var modelBatch: ModelBatch
    lateinit var environment: Environment
    lateinit var camController: CameraInputController
    
    init {
        modelBatch = ModelBatch()
        environment = Environment()
        environment.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = PerspectiveCamera(fov, viewport.x, viewport.y)
        cam.position.set(0f, 10f, 4f);
        cam.lookAt(0f, 0f, 0f);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();

        camController = CameraInputController(cam)
        Gdx.input.inputProcessor = camController
    }
    
    public fun render (visibleModels: Array<VisibleModel>)
    {
        Gdx.gl.glViewport(0, 0, viewport.x.toInt(), viewport.y.toInt())
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        modelBatch.begin(cam)

        for (model in visibleModels)
            model.render(modelBatch, environment)

        modelBatch.end()
    }

    public fun resize (width: Int, height: Int)
    {
        viewport.set(width.toFloat(), height.toFloat())
        cam.viewportWidth = width.toFloat()
        cam.viewportHeight = height.toFloat()
        cam.update()
    }
}