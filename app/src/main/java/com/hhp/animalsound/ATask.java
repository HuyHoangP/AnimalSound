package com.hhp.animalsound;

import android.os.AsyncTask;

import java.util.Objects;

public class ATask extends AsyncTask<Object, Object, Object> {
    private final String key;
    private final OnATaskCallback callback;

    public ATask(String key, OnATaskCallback callback) {
        this.key = key;
        this.callback = callback;
    }
    @Override
    protected void onPreExecute() {
        callback.preExec(key);
    }

    @Override
    protected Object doInBackground(Object... objects) {
        return callback.execTask(key, objects == null? null : objects[0], this);
    }

    public void requestUI(Object...data){
        publishProgress(data);
    }

    @Override
    protected void onProgressUpdate(Object... data) {
        callback.updateUI(key, data[0]);
    }

    @Override
    protected void onPostExecute(Object result) {
        callback.completeTask(key, result);
    }

    @Override
    protected void onCancelled(Object result) {
        callback.cancelTask(key, result);
    }

    public void start(Object...params){
        execute(params);
    }
    public void startAsync(Object...params){
        executeOnExecutor(THREAD_POOL_EXECUTOR, params);
    }
    public void stop(){
        cancel(true);
    }

    public interface OnATaskCallback{
        default void preExec(String key){};
        Object execTask(String key, Object param, ATask task);
        default void updateUI(String key, Object data){};
        default void completeTask(String key, Object result){};
        default void cancelTask(String key, Object result){};
    }
}
