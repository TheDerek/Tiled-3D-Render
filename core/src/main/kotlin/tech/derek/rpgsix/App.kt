package tech.derek.rpgsix

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Vector2

class App : ApplicationAdapter()
{
    var viewport = Vector2(640f, 480f);
    val fov = 70f;

    lateinit var cam: PerspectiveCamera
    lateinit var model: Model
    lateinit var instance: ModelInstance
    lateinit var modelBatch: ModelBatch
    lateinit var environment: Environment
    lateinit var camController: CameraInputController
    lateinit var wallTexture: Texture

    override fun create()
    {
        wallTexture = Texture("wall.png")

        modelBatch = ModelBatch()
        environment = Environment()
        environment.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = PerspectiveCamera(fov, viewport.x, viewport.y)
        cam.position.set(4f, 20f, 4f);
        cam.lookAt(0f, 0f, 0f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = CameraInputController(cam)
        Gdx.input.inputProcessor = camController

        val modelBuilder = ModelBuilder();
        model = modelBuilder.createBox(1f, 1f, 1f,
                Material(TextureAttribute.createDiffuse(wallTexture)),
                (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal or VertexAttributes.Usage.TextureCoordinates).toLong());
        instance = ModelInstance(model);
    }

    override fun render()
    {
        Gdx.gl.glViewport(0, 0, viewport.x.toInt(), viewport.y.toInt())
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        modelBatch.begin(cam)
        modelBatch.render(instance, environment)
        modelBatch.end()
    }


    override fun resize (width: Int, height: Int)
    {
        viewport.set(width.toFloat(), height.toFloat())
        cam.viewportWidth = width.toFloat()
        cam.viewportHeight = height.toFloat()
        cam.update()
    }


    override fun dispose()
    {
        modelBatch.dispose()
        model.dispose()
        super.dispose()
    }
}
