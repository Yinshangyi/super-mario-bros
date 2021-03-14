package com.games.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.games.mario.MarioBros;
import com.games.mario.scenes.Hud;
import com.games.mario.sprites.Goomba;
import com.games.mario.sprites.Mario;
import com.games.mario.tools.B2WorldCreator;
import com.games.mario.tools.GameMaths;
import com.games.mario.tools.WorldContactListener;

public class PlayScreen implements Screen {

    private MarioBros game;
    private TextureAtlas atlas;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2br;

    // Sprites
    private Mario player;
    private Goomba goomba;

    private Music music;

    public PlayScreen(MarioBros game){
        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(GameMaths.scaleValue(MarioBros.V_WIDTH),
                GameMaths.scaleValue(MarioBros.V_HEIGHT), gameCam);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, GameMaths.scaleValue(1));

        float gameCamPosX = gamePort.getWorldWidth()/2;
        float gameCamPosY = gamePort.getWorldHeight()/2;
        gameCam.position.set(gameCamPosX, gameCamPosY, 0);

        world = new World(new Vector2(0, -10), true);
        player = new Mario(world, this);
        world.setContactListener(new WorldContactListener());

        b2br = new Box2DDebugRenderer();

        new B2WorldCreator(this);

        music = MarioBros.manager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        music.play();

        goomba = new Goomba(this, .32f, .32f);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(
                    new Vector2(0, 4f),
                    player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(
                    new Vector2(0.1f, 0),
                    player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(
                    new Vector2(-0.1f, 0),
                    player.b2body.getWorldCenter(), true);
        }


    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f,6, 2);
        player.update(dt);
        goomba.update(dt);
        hud.update(dt);
        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        /* Separate update logic from render */
        update(delta);

        /* Clear the game screen with a black color */
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Render our game map */
        renderer.render();

        /* Render our Box2DDebugLines */
        b2br.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        goomba.draw(game.batch);
        game.batch.end();


        /* Set our batch to now draw what the Hud camera sees */
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2br.dispose();
        hud.dispose();
    }
}
