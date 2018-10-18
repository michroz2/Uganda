package com.mich.games.uganda;


import com.mich.common.gdx.App;
import com.mich.common.gdx.api.screen.ScreenApi;
import com.mich.common.gdx.api.screen.ScreenApiImpl;
import com.mich.games.uganda.screens.MainScreen;

public class UgandaGame extends App {


    public UgandaGame() {
        put(ScreenApi.class, new ScreenApiImpl());
    }

    @Override
    public void create() {
        super.create();

        App.get(ScreenApi.class).setScreen(MainScreen.class);
    }
}
