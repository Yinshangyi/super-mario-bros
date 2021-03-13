package com.games.mario.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.games.mario.MarioBros;
import com.games.mario.tools.GameMaths;

public class Mario extends Sprite {

    public World world;
    public Body b2body;

    public Mario(World world) {
        this.world = world;
        defineMario();
    }

    public void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(GameMaths.scaledValue(32), GameMaths.scaledValue(32));
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(GameMaths.scaledValue(5));
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
