package com.games.mario.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.games.mario.MarioBros;
import com.games.mario.tools.GameMaths;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;

        float bdefPX = GameMaths.scaledValue((bounds.getX() + bounds.getWidth()/2));
        float bdefY = GameMaths.scaledValue((bounds.getY() + bounds.getHeight()/2));
        bdef.position.set(bdefPX, bdefY);

        body = world.createBody(bdef);
        shape.setAsBox(GameMaths.scaledValue(bounds.getWidth()/2),
                GameMaths.scaledValue(bounds.getHeight()/2));
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
