package com.games.mario.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.games.mario.screens.PlayScreen;
import com.games.mario.tools.GameMaths;

public class Mario extends Sprite {

    public World world;
    public Body b2body;
    private TextureRegion marioStand;
    private final int SPRITE_WIDTH = 16;
    private final int SPRITE_HEIGHT = 16;

    public Mario(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 1, 11, SPRITE_WIDTH, SPRITE_HEIGHT);
        setBounds(0, 0, GameMaths.scaledValue(SPRITE_WIDTH), GameMaths.scaledValue(SPRITE_HEIGHT));
        setRegion(marioStand);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }

    public void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(GameMaths.scaledValue(32), GameMaths.scaledValue(32));
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(GameMaths.scaledValue(6));
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
