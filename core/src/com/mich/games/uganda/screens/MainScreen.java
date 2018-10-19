package com.mich.games.uganda.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mich.common.gdx.App;
import com.mich.common.gdx.api.AssetsApi;
import com.mich.common.gdx.screen.GenericScreen;

import javax.swing.*;

public class MainScreen extends GenericScreen {

    public MainScreen() {




    }

    @Override
    public void show() {
        super.show();

        Texture texture = App.get(AssetsApi.class).get("badlogic.jpg", Texture.class);
        Image img = new Image(texture);
        addActor(img);

    }
}
