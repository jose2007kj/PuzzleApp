package com.slideingpuzzle.puzzleapp;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.SoundPool;
import android.os.Build;

public class SoundManager {
    private SoundPool pool;
    private Context context;
    private int streamId = 0;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SoundManager(Context context){
        this.context = context;
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        pool = builder.build();
    }

    public int addSound(int resourceID){
        return pool.load(context, resourceID, 1);
    }

    public void play(int soundID){

        streamId=pool.play(soundID, 1, 1, 1, 0,1);
    }
    public void releaseSoundPool(){
        if (pool != null) {
            pool.release();
            pool = null;
        }
    }
    //code for pausing
    public void pause(){
        if(streamId!=0) {
            pool.pause(streamId);
        }
    }
    //code for resuming
    public void resume(){
        if(streamId!=0){
            pool.resume(streamId);
        }
    }
}