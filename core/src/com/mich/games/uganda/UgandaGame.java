package com.mich.games.uganda;


import com.mich.common.gdx.App;
import com.mich.games.uganda.api.AssetsApi;
import com.mich.common.gdx.screen.ScreenApi;
import com.mich.common.gdx.screen.ScreenApiImpl;
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


        App.get(ScreenApi.class).setScreen(MainScreen.class);
    }


}
