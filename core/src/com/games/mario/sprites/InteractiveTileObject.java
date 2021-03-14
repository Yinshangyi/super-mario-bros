package com.games.mario.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.games.mario.screens.PlayScreen;
import com.games.mario.tools.GameMaths;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(PlayScreen screen, Rectangle bounds){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;

        float bdefPX = GameMaths.scaleValue((bounds.getX() + bounds.getWidth()/2));
        float bdefY = GameMaths.scaleValue((bounds.getY() + bounds.getHeight()/2));
        bdef.position.set(bdefPX, bdefY);

        body = world.createBody(bdef);
        shape.setAsBox(GameMaths.scaleValue(bounds.getWidth()/2),
                GameMaths.scaleValue(bounds.getHeight()/2));
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHeadHit();

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        int cellX = (int) GameMaths.deScaleValue(body.getPosition().x) / 16;
        int cellY = (int) GameMaths.deScaleValue(body.getPosition().y) / 16;
        return layer.getCell(cellX, cellY);
    }
}
