package com.simanglam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Player implements InputProcessor{
    static int MOVING_SPEED = 4;
    Sprite sprite;
    public Vector2 heading;
    Vector2 lastHeading;
    public Player(){
        this.sprite = new Sprite(new Texture("character.png"));
        this.heading = new Vector2(0, 0);
        this.lastHeading = this.heading.cpy();
    }

    public void draw(SpriteBatch batch){ sprite.draw(batch); }
    public void translate(float x, float y){ sprite.translate(x, y); }
    public void setPosition(float x, float y){ sprite.setPosition(x, y); }

    public boolean isHeadLeft(){return heading.x == -1;}
    public boolean isHeadDown(){return heading.y == -1;}
    public boolean isHeadRight(){return heading.x == 1;}
    public boolean isHeadUP(){return heading.y == 1;}
    public Vector2 getLastHeading(){return lastHeading;}

    public Sprite getSprite(){return sprite;}
    public Vector2 getPosition(){return new Vector2(this.sprite.getBoundingRectangle().x, this.sprite.getBoundingRectangle().y); }
    public Rectangle creatInvestgateRectangle(){
        return new Rectangle((float)Math.floor(this.sprite.getBoundingRectangle().x / 16) * 16 + lastHeading.x * 16, (float)Math.floor(this.sprite.getBoundingRectangle().y / 16) * 16 + lastHeading.y * 16, 16, 16);
    }

    public void updateX(){
        Vector2 mVector2 = heading.cpy().scl(MOVING_SPEED);
        sprite.translate(mVector2.x, 0);
    }

    public void updateY(){
        Vector2 mVector2 = heading.cpy().scl(MOVING_SPEED);
        sprite.translate(0, mVector2.y);
    }

    public void collide(Rectangle rectangle){

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode != Keys.A && keycode != Keys.S && keycode != Keys.W && keycode != Keys.D){
            return false;
        }
        if (keycode == Keys.A){
            heading.x -= 1;
        }
        else if (keycode == Keys.S){
            heading.y -= 1;
        }
        else if (keycode == Keys.D){
            heading.x += 1;
        }
        else if (keycode == Keys.W){
            heading.y += 1;
        }
        lastHeading.set(this.heading);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode != Keys.A && keycode != Keys.S && keycode != Keys.W && keycode != Keys.D){
            return false;
        }
        if (keycode == Keys.A){
            heading.x += 1;
        }
        else if (keycode == Keys.S){
            heading.y += 1;
        }
        else if (keycode == Keys.D){
            heading.x -= 1;
        }
        else if (keycode == Keys.W){
            heading.y -= 1;
        }
        return true;
    }
    @Override
    public boolean keyTyped(char character) { return false; }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override
    public boolean scrolled(float amountX, float amountY) { return false; }
}
