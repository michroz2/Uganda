package com.mich.games.uganda;


import com.badlogic.gdx.Application;
import com.mich.common.gdx.App;
import com.mich.common.gdx.Log;
import com.mich.common.gdx.screen.ScreenApi;
import com.mich.common.gdx.screen.ScreenApiImpl;
import com.mich.games.uganda.api.AssetsApi;
import com.mich.games.uganda.screens.MainScreen;

public class UgandaGame extends App {


    public UgandaGame() {
        put(ScreenApi.class, new ScreenApiImpl());
        put(AssetsApi.class, new AssetsApi());

        AssetsApi assetsApi = get(AssetsApi.class);




    }

    @Override
    public void create() {
        super.create();

        Log.setLogLevel(Application.LOG_DEBUG);

        init();

        App.get(ScreenApi.class).setScreen(MainScreen.class);
    }


}
