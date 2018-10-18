package com.mich.common.gdx.api.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.scenes.scene2d.Group;

public interface Screen {
    /**
     * Called when this screen becomes the current screen.
     */
    void show();

    /**
     * Called when this screen is no longer the current screen.
     */
    void hide();

    /**
     * @see ApplicationListener#pause()
     */
    void pause();

    /**
     * @see ApplicationListener#resume()
     */
    void resume();

    /**
     * Called when this screen should release all resources.
     */
    void dispose();

    Group getRoot();

}
