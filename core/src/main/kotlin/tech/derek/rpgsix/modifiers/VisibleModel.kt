package tech.derek.rpgsix.modifiers

import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.math.Quaternion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

interface VisibleModel
{
    val instance: ModelInstance
    val position: Vector3
    val rotation: Float


    fun render(modelBatch: ModelBatch, environment: Environment)
    {
        instance.transform.setToRotation(Vector3.Y, rotation).trn(position)
        modelBatch.render(instance, environment)
    }
}