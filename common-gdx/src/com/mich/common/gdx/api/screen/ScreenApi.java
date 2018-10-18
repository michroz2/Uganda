package com.mich.common.gdx.api.screen;

public interface ScreenApi {

    /**
     * set screen of specified type
     * @param type screen type
     */
    void setScreen(Class<? extends Screen> type);
}
