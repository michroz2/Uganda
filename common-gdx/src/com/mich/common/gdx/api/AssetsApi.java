package com.mich.common.gdx.api;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

public class AssetsApi {

    AssetManager manager;

    public AssetsApi() {

        manager = new AssetManager();

        FileHandleResolver resolver = new InternalFileHandleResolver();

        manager.setLoader(Texture.class, new TextureLoader(resolver));

        loadTexture("badlogic.jpg");


    }

    public void loadTexture(String name) {
        manager.load(name, Texture.class );



    }

    public <T> T get(String name, Class<T> type) {
        return manager.get(name, type);
    }


}
