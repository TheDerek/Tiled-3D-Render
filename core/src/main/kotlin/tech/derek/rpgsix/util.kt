package tech.derek.rpgsix

import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.maps.tiled.TiledMapTile
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.math.Vector3
import tech.derek.rpgsix.modifiers.VisibleModel
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ObjectMap
import tech.derek.rpgsix.entites.Block
import tech.derek.rpgsix.entites.createBlockModel


public fun loadMap(filePath: String, visibleModels: Array<VisibleModel>)
{
    val map = TmxMapLoader().load(filePath);

    // Create models for each tile type present in the tiled map
    val tileMap = ObjectMap<TiledMapTile, Model>()
    for(tileset in map.tileSets)
        for(tile in tileset)
            tileMap.put(tile, createBlockModel(tile.textureRegion))


    // Place the tiles into the array
    for(z in 0..map.layers.count - 1)
    {
        val layer = map.layers.get(z)
        val tLayer = layer as TiledMapTileLayer

        fun addTile(x: Int, y: Int)
        {
            val cell = tLayer.getCell(x, y);
            if(cell != null)
                visibleModels.add(Block(Vector3(x.toFloat(),
                        z.toFloat(), y.toFloat()), tileMap.get(cell.tile)))
        }

        operate(tLayer, fun(x, y) = addTile(x, y))
    }
}

fun operate(layer: TiledMapTileLayer, operation: (x: Int, y: Int) -> Unit)
{
    for(y in 0..layer.height)
        for(x in 0..layer.width)
            operation(x, y);
}