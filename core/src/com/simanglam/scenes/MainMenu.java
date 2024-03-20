package com.simanglam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.simanglam.Main;

import aurelienribon.bodyeditor.BodyEditorLoader;

public class MainMenu implements Screen {
    Main game;
    Texture img;
    World world;
    BodyEditorLoader loader;
    Sprite basicImage;
    Body basicModel;
    Vector2 bottleModelOrigin;


    public MainMenu(final Main game){
        this.game = game;
        this.img = new Texture("badlogic.jpg");
        this.world = new World(new Vector2(0, -10), false);
        this.loader = new BodyEditorLoader(Gdx.files.internal("sprite.json"));
        basicImage = new Sprite(new Texture("badlogic.jpg"));
        // 1. Create a BodyDef, as usual.
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DynamicBody;

        // 2. Create a FixtureDef, as usual.
        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;

        // 3. Create a Body, as usual.
        basicModel = world.createBody(bd);

        // 4. Create the body fixture automatically by using the loader.
        loader.attachFixture(basicModel, "Name", fd, 10);
        bottleModelOrigin = loader.getOrigin("Name", 10).cpy();
        bottleModelOrigin.set(0, 0);
    }

    public void render(float deltaT){
        SpriteBatch batch = game.getSpriteBatch();
        ScreenUtils.clear(0, 0, 0, 0);
        world.step(deltaT, 10, 10);

        Vector2 bottlePos = basicModel.getPosition().sub(bottleModelOrigin);
        basicImage.setPosition(bottlePos.x, bottlePos.y);
        basicImage.setOrigin(bottleModelOrigin.x, bottleModelOrigin.y);
        batch.begin();
        basicImage.draw(batch);
        batch.end();

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
        
    }

    public void dispose(){
        img.dispose();
    }

}
