package com.mich.common.gdx;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Holder for unique objects such as APIs and Managers
 */
public class ApiHolder implements AppListener, Renderable {

    private ObjectMap<Class<?>, Object> map = new ObjectMap<Class<?>, Object>(64);

    private SnapshotArray<Object> appListeners = new SnapshotArray<Object>(50);
    SnapshotArray<Renderable> renderers = new SnapshotArray<Renderable>(Renderable.class);


    @SuppressWarnings("unchecked")
    public <T> T remove(Class<T> key) {
        T remove = (T) map.remove(key);
        removeListener(remove);
        return remove;
    }

    public <T> void put(Class<T> key, T value) {
        map.put(key, value);
        addListener(value);

        if (value instanceof Renderable) {
            renderers.add((Renderable) value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void put(T value) {
        put((Class<T>) value.getClass(), value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> key) {
        return (T) map.get(key);
    }

    public <T> boolean contains(Class<T> key) {
        return map.containsKey(key);
    }


    @Override
    public void create() {
        Object[] items = appListeners.begin();
        for (int i = 0, n = appListeners.size; i < n; i++) {
            try {
                Object item = items[i];
                if (item instanceof AppListener) {
                    ((AppListener) item).create();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        appListeners.end();
    }

    @Override
    public void resize(int width, int height) {
        Object[] items = appListeners.begin();
        for (int i = 0, n = appListeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).resize(width, height);
            }
        }
        appListeners.end();
    }

    @Override
    public void pause() {
        Object[] items = appListeners.begin();
        for (int i = 0, n = appListeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).pause();
            }
        }
        appListeners.end();
    }

    @Override
    public void resume() {
        Object[] items = appListeners.begin();
        for (int i = 0, n = appListeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).resume();
            }
        }
        appListeners.end();
    }


    @Override
    public void dispose() {
        Object[] items = appListeners.begin();
        for (int i = 0, n = appListeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).dispose();
            }

        }
        appListeners.end();
        destroy();
    }

    void destroy() {
        map.clear();
        appListeners.clear();

    }

    <T> void addListener(T value) {
        if (value instanceof AppListener) {
            appListeners.add(value);
        }
    }

    private <T> void removeListener(T value) {
        if (value instanceof AppListener) {
            appListeners.removeValue(value, true);
        }
    }

    @Override
    public void render(float delta) {
        Renderable[] items = renderers.begin();
        for (int i = 0, n = renderers.size; i < n; i++) {
            items[i].render(delta);
        }
        renderers.end();
    }
}
