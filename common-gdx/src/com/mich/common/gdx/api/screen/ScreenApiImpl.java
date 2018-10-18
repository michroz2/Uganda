package com.mich.common.gdx.api.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mich.common.gdx.AppHandler;
import com.mich.common.gdx.Renderable;

public class ScreenApiImpl extends AppHandler implements ScreenApi, Renderable {

    private Stage stage;
    private Batch batch;
    private boolean sizeChanged;


    @Override
    public void create() {
        super.create();

        batch = new SpriteBatch();
        stage = createStage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void setScreen(Class<? extends Screen> type) {
        Group root = stage.getRoot();

        try {
            Screen screen = ClassReflection.newInstance(type);
            root.addActor(screen.getRoot());
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (sizeChanged) {
            Viewport viewport = stage.getViewport();
            viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            sizeChanged = false;
        }

        stage.act(delta);

        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage = dispose(stage);
        batch = dispose(batch);
    }

    private Stage createStage(float viewportWidth, float viewportHeight) {
        Viewport viewport = new StretchViewport(viewportWidth, viewportHeight);

        Stage stage = new Stage(viewport, batch);

        Group root = new Group();

        stage.setRoot(root);

        return stage;
    }


}
