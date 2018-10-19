package com.mich.games.uganda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mich.common.gdx.screen.GenericScreen;

public class MainScreen extends GenericScreen {

    public MainScreen() {


    }

    @Override
    public void show() {
        super.show();
        System.out.println(">show");

        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));

        System.out.println(">>> t: " + texture);
        Image img = new Image(texture);
        addActor(img);



    }
}
