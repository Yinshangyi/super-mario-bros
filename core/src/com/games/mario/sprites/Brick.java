package com.games.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.games.mario.MarioBros;
import com.games.mario.scenes.Hud;

public class Brick extends InteractiveTileObject {
    public Brick(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Collision", "Brick");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
    }
}
