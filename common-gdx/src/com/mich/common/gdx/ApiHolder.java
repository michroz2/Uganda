package com.mich.common.gdx;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Holder for unique objects such as APIs and Managers
 */
public class ApiHolder implements AppListener, Renderable, InitListener {

    private static final String TAG = "ApiHolder";

    SnapshotArray<Renderable> renderers = new SnapshotArray<Renderable>(Renderable.class);
    private ObjectMap<Class<?>, Object> map = new ObjectMap<Class<?>, Object>(64);
    private SnapshotArray<Object> listeners = new SnapshotArray<Object>(50);


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
        Object[] items = listeners.begin();
        for (int i = 0, n = listeners.size; i < n; i++) {
            try {
                Object item = items[i];
                if (item instanceof AppListener) {
                    ((AppListener) item).create();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        listeners.end();
    }

    @Override
    public void resize(int width, int height) {
        Object[] items = listeners.begin();
        for (int i = 0, n = listeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).resize(width, height);
            }
        }
        listeners.end();
    }

    @Override
    public void pause() {
        Object[] items = listeners.begin();
        for (int i = 0, n = listeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).pause();
            }
        }
        listeners.end();
    }

    @Override
    public void resume() {
        Object[] items = listeners.begin();
        for (int i = 0, n = listeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).resume();
            }
        }
        listeners.end();
    }


    @Override
    public void dispose() {
        Object[] items = listeners.begin();
        for (int i = 0, n = listeners.size; i < n; i++) {
            Object item = items[i];
            if (item instanceof AppListener) {
                ((AppListener) item).dispose();
            }

        }
        listeners.end();
        destroy();
    }

    void destroy() {
        map.clear();
        listeners.clear();

    }

    <T> void addListener(T value) {
        if (value instanceof AppListener || value instanceof InitListener) {
            listeners.add(value);
        }


    }

    private <T> void removeListener(T value) {
        if (value instanceof AppListener || value instanceof InitListener) {
            listeners.removeValue(value, true);
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

    @Override
    public void init() {
        Object[] items = listeners.begin();

        for (int i = 0, n = listeners.size; i < n; i++) {
            Object item = items[i];
            Log.debug(TAG, "init - " + item.getClass().getSimpleName());
            if (item instanceof InitListener) {
                ((InitListener) item).init();
            }
        }
        listeners.end();
    }
}
