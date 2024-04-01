package com.simanglam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class Dialog extends Window {
    static float a = 0.04f;
    String description;
    int cursor;
    float deltaT;
    BitmapFont font;
    Stage stage;
    boolean show;
    Label label;
    public Dialog(final Stage stage){
        super("Dialog", new Skin(Gdx.files.internal("data/uiskin.json")));
        this.stage = stage;
        deltaT = 0.0f;
        description = "A";
        cursor = 1;
        label = new Label(description, getSkin());
        setPosition(0, 0);
        setColor(new Color(255, 255, 255, 255));
        setWidth(stage.getWidth());
        setHeight(stage.getHeight() / 5);
        this.add(label);
        this.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keyCode){
                Actor a = event.getListenerActor();
                a.remove();
                return false;
            }
        });
    }

    public void act(float deltaT){
        this.deltaT += deltaT;
        if (this.deltaT > a){
            cursor = (cursor++ >= description.length()) ? description.length() : cursor;
            label.setText(description.substring(0, cursor));
            this.deltaT -= a;
        }
    }

    public void draw(Batch batch){
        font.draw(batch, description.substring(0, cursor), 0, 0);
    }

    public void setDescription(String description){
        this.description = description;
        cursor = 1;
        label.setText(description.substring(0, cursor));
    }
}

