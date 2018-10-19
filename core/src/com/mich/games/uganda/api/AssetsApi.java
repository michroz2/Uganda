package com.mich.games.uganda.api;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.mich.common.gdx.InitListener;
import com.mich.common.gdx.Renderable;

public class AssetsApi implements InitListener, Renderable {

    private AssetManager manager;

    public AssetsApi() {
        manager = new AssetManager();
    }

    @Override
    public void init() {
        FileHandleResolver resolver = new InternalFileHandleResolver();

        manager.setLoader(Texture.class, new TextureLoader(resolver));

        loadTexture("badlogic.jpg");


        manager.finishLoading(); // fixme:
    }

    @Override
    public void render(float delta) {
        // todo: load assets here
    }


    private void loadTexture(String name) {
        manager.load(name, Texture.class);
    }

    public Texture getTexture(String name) {
        return manager.get(name, Texture.class);
    }
}
