package com.simanglam.scenes;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.simanglam.Main;
import com.simanglam.Player;

public class MainMenu implements Screen{
    Main game;
    Texture img;
    World world;
    Stage stage;
    OrthographicCamera camera;
    Vector2 middle;
    TiledMap tiledMap;
    TiledMapRenderer renderer;
    InputMultiplexer inputMultiplexer;
    Logger logger;
    Player player;

    public MainMenu(final Main game){
        this.game = game;
        this.img = new Texture(Gdx.files.internal("badlogic.jpg"));
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new ExtendViewport(640, 480, camera));
        this.player = new Player();
        this.player.getSprite().setPosition(this.camera.position.x, this.camera.position.y);
        this.middle = new Vector2(1200, 1000).scl(.5f);
        this.tiledMap = new TmxMapLoader().load("test.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.logger = Logger.getLogger("Main");
        this.logger.setLevel(Level.ALL);
        this.inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer.addProcessor(this.player);
        Gdx.input.setInputProcessor(this.inputMultiplexer);
    }

    public void render(float deltaT){
        ScreenUtils.clear(0, 0, 0, 0);
        SpriteBatch batch = game.getSpriteBatch();
        player.updateX();
        collideDetectX();
        player.updateY();
        collideDetectY();

        this.camera.position.set(player.getPosition(), 0);
        stage.getViewport().apply();
        this.camera.update();
        batch.setProjectionMatrix(this.stage.getCamera().combined);
        this.renderer.setView(camera);
        this.renderer.render();
        batch.begin();
        this.player.getSprite().draw(batch);
        batch.end();
    }

    private void collideDetectX(){
        MapLayer collisionObjectLayer = tiledMap.getLayers().get("物件層 1");
        MapObjects objects = collisionObjectLayer.getObjects();
        Rectangle playerRectangle = player.getSprite().getBoundingRectangle();
        // there are several other types, Rectangle is probably the most common one
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(playerRectangle, rectangle)) {
                if (player.isHeadLeft()){
                    player.setPosition(rectangle.x + rectangle.getWidth(), playerRectangle.y);
                }
                else{
                    player.setPosition(rectangle.x - playerRectangle.getWidth(), playerRectangle.y);
                }
            }
        }
    }

    private void collideDetectY(){
        MapLayer collisionObjectLayer = tiledMap.getLayers().get("物件層 1");
        MapObjects objects = collisionObjectLayer.getObjects();
        Rectangle playerRectangle = player.getSprite().getBoundingRectangle();
        // there are several other types, Rectangle is probably the most common one
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(playerRectangle, rectangle)) {
                if (player.isHeadDown()){
                    player.setPosition(playerRectangle.x, rectangle.y + rectangle.getHeight());
                }
                else{
                    player.setPosition(playerRectangle.x, rectangle.y - playerRectangle.getHeight());
                }
            }
        }
    }

    public void show(){
        
    }

    public void hide(){
        
    }

    public void pause(){
        
    }

    public void resume(){
        
    }

    public void resize(int x, int y){
        this.stage.getViewport().update(x, y);
    }

    public void dispose(){
        img.dispose();
    }

}
