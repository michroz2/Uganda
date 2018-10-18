package com.mich.common.gdx;

/**
 * An <code>GameListener</code> is called when the Game is created, resumed, paused or destroyed. All methods are called in a thread that has the
 * OpenGL context current. You can thus safely create and manipulate graphics resources. </p>
 * 
 *
 */
public interface AppListener {

    /** Called when the Game is first created. */
    void create();

    /**
     * Called when the Game is resized. This can happen at any point during a non-paused state but will never happen before a call to
     * {@link #create()}.
     * 
     * @param width
     *            the new width in pixels
     * @param height
     *            the new height in pixels
     */
    void resize(int width, int height);

    /**
     * Called when the Game is paused. An Application is paused before it is destroyed, when a user pressed the Home button on Android or an incoming
     * call happend. On the desktop this will only be called immediately before {@link #dispose()} is called.
     */
    void pause();

    /**
     * Called when the Game is resumed from a paused state. On Android this happens when the activity gets focus again. On the desktop this method
     * will never be called.
     */
    void resume();

    /**
     * Called when the Game is destroyed. Preceded by a call to {@link #pause()}.
     */
    void dispose();

}
