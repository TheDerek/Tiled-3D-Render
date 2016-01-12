package tech.derek.rpgsix.entites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Vector3
import tech.derek.rpgsix.modifiers.VisibleModel
import java.text.FieldPosition

// Static stuff, this applies to all Wall instances
val texture = Texture("wall.png")
val modelBuilder = ModelBuilder();
val model = modelBuilder.createBox(1f, 1f, 1f,
        Material(TextureAttribute.createDiffuse(texture)),
        (VertexAttributes.Usage.Position
                or VertexAttributes.Usage.Normal
                or VertexAttributes.Usage.TextureCoordinates)
                .toLong());

class Block(val pos: Vector3) : VisibleModel
{
    override val position: Vector3 = pos.cpy()
    override val rotation: Float = 0f
    override val instance: ModelInstance = ModelInstance(model)
}