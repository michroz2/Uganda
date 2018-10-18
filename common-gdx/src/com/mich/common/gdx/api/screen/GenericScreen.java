package com.mich.common.gdx.api.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class GenericScreen implements Screen {

    protected final Group root;


    public GenericScreen() {
        root = new Group() {
            @Override
            public void act(float delta) {
                if (!isVisible()) return;
                super.act(delta);
            }
        };
        root.setName(getClass().getSimpleName());
        root.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        root.clear();

    }

    @Override
    public Group getRoot() {
        return root;
    }

    public void addActor(Actor actor, float x, float y) {
        actor.setPosition(x, y);
        addActor(actor);
    }

    public void addActor(Actor t) {
        root.addActor(t);
    }

    public void addActors(Actor... actor) {
        for (Actor a : actor) {
            addActor(a);
        }
    }


}
