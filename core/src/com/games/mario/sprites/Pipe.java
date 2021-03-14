package com.games.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.games.mario.MarioBros;
import com.games.mario.screens.PlayScreen;

public class Pipe extends InteractiveTileObject {

    public Pipe(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        setCategoryFilter(MarioBros.OBJECT_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Collision", "Pipe");
    }
}
