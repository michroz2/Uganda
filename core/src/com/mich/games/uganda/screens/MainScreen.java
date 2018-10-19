package com.mich.games.uganda.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mich.common.gdx.App;
import com.mich.games.uganda.api.AssetsApi;
import com.mich.common.gdx.screen.GenericScreen;


public class MainScreen extends GenericScreen {

    public MainScreen() {

//        AssetsApi assetsApi = App.get(AssetsApi.class);
//        Texture texture = assetsApi.getTexture("badlogic.jpg");
//
//        System.out.println(">>> t: " + texture);
//        Image img = new Image(texture);
//        addActor(img);
    }

    @Override
    public void show() {
        super.show();
        System.out.println(">show");





    }
}
