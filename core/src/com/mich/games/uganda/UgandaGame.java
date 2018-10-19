package com.mich.games.uganda;


import com.badlogic.gdx.graphics.Texture;
import com.mich.common.gdx.App;
import com.mich.common.gdx.screen.ScreenApi;
import com.mich.common.gdx.screen.ScreenApiImpl;
import com.mich.games.uganda.api.AssetsApi;
import com.mich.games.uganda.screens.MainScreen;

public class UgandaGame extends App {


    public UgandaGame() {
        put(ScreenApi.class, new ScreenApiImpl());
        put(AssetsApi.class, new AssetsApi());

        AssetsApi assetsApi = get(AssetsApi.class);
        System.out.println(">>> ");


    }

    @Override
    public void create() {
        super.create();

        init();

        Texture texture = new Texture("badlogic.jpg");
        System.out.println(">>> te:  " + texture);
        App.get(ScreenApi.class).setScreen(MainScreen.class);
    }


}
