package com.mich.common.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class App extends ApplicationAdapter {

    private static ApiHolder apiHolder;

    protected App() {
        apiHolder = new ApiHolder();

    }

    @Override
    public void dispose() {
        super.dispose();
        apiHolder.dispose();
        apiHolder = null;
    }


    @Override
    public void create() {
        super.create();
        apiHolder.create();
    }

    @Override
    public void pause() {
        super.pause();

        apiHolder.pause();
    }

    @Override
    public void resume() {
        apiHolder.resume();
    }

    @Override
    public void render() {
        super.render();
        apiHolder.render(Gdx.graphics.getDeltaTime());
    }


    public static <T> void put(Class<T> key, T value) {
        apiHolder.put(key, value);
    }

    public static <T> void put(T value) {
        apiHolder.put(value);
    }

    public static <T> T get(Class<T> key) {
        return apiHolder.get(key);
    }




}
