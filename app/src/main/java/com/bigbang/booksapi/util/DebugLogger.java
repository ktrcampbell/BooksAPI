package com.bigbang.booksapi.util;

import android.util.Log;

import static com.bigbang.booksapi.util.Constants.*;

public class DebugLogger {
    public static void logError(Throwable throwable){
        Log.d(TAG, ERROR + throwable.getLocalizedMessage());

    }

    public static void logDebug(String message){
        Log.d(TAG, message);
    }
}
