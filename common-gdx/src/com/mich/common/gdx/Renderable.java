package com.mich.common.gdx;


import com.badlogic.gdx.Application;

public interface Renderable {

    /**
     * Called when the {@link Application} should render itself.
     */
    void render(float delta);

    class Methods {
        public static void render(float delta, Renderable... re) {
            for (Renderable r : re) {
                render(delta, r);
            }
        }

        public static void render(float delta, Renderable r) {
            if (r != null) {
                r.render(delta);
            }
        }
    }
}