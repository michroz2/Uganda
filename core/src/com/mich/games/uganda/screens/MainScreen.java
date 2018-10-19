package com.mich.games.uganda.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mich.common.gdx.App;
import com.mich.common.gdx.screen.GenericScreen;
import com.mich.games.uganda.api.AssetsApi;


public class MainScreen extends GenericScreen {

    private AssetsApi assetsApi = App.get(AssetsApi.class);

    public MainScreen() {

        Texture texture = assetsApi.getTexture("badlogic.jpg");

        final Image img = new Image(texture);
        addActor(img);

        img.addAction(Actions.forever(Actions.sequence(Actions.fadeOut(.5f), Actions.fadeIn(.5f))));


    }

    @Override
    public void show() {
        super.show();

    }
}
