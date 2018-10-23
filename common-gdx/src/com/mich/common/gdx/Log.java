package com.mich.common.gdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;


/**
 * Convenient class for GDX logging, can be used only after Gdx application initialization
 */
public class Log {
    public static final String TAG = "AppLog";


    public static boolean isDebugEnabled() {
        return Gdx.app.getLogLevel() >= Application.LOG_DEBUG || Gdx.app.getType() == Application.ApplicationType.WebGL;
    }


    public static boolean isInfoEnabled() {
        return Gdx.app.getLogLevel() >= Application.LOG_INFO;
    }


    public static void debug(Object message) {
        Gdx.app.debug(TAG, String.valueOf(message));
    }


    public static void debug(String tag, Object message) {
        Gdx.app.debug(tag, String.valueOf(message));
    }

    public static void info(Object message) {
        Gdx.app.log(TAG, String.valueOf(message));
    }

    public static void info(String tag, Object message) {
        Gdx.app.log(tag, String.valueOf(message));
    }

    public static void error(Object message) {
        Gdx.app.error(TAG, String.valueOf(message));
    }

    public static void error(String tag, Object message) {
        Gdx.app.error(tag, String.valueOf(message));
    }

    public static void error(Object message, Throwable t) {
        Gdx.app.error(TAG, String.valueOf(message), t);
    }

    public static void error(String tag, Object message, Throwable t) {
        Gdx.app.error(tag, String.valueOf(message), t);
    }

    /**
     * Sets the log level. {@link  Application#LOG_NONE} will mute all log output. {@link Application#LOG_ERROR} will only let error messages through. {@link Application#LOG_INFO} will
     * let all non-debug messages through, and {@link Application#LOG_DEBUG} will let all messages through.
     *
     * @param logLevel {Application.LOG_NONE}, {Application.LOG_ERROR}, {Application.LOG_INFO}, {Application.LOG_DEBUG}.
     */
    public static void setLogLevel(int logLevel) {
        Gdx.app.setLogLevel(logLevel);
    }


}