package com.games.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.games.mario.screens.PlayScreen;

public class Ground extends InteractiveTileObject {
    public Ground(PlayScreen screen, Rectangle bounds) {

        super(screen, bounds);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Collision", "Ground");
    }
}
