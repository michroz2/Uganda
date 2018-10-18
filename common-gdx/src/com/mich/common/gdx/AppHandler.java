package com.mich.common.gdx;


import com.badlogic.gdx.utils.Disposable;

/**
 * Convenient class if you need to fire/listen notices and listen application life cycle
 */
public class AppHandler implements AppListener {

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

    protected <T> T dispose(Disposable disposable) {
        if (disposable != null) {
            disposable.dispose();
        }
        return null;
    }

}
