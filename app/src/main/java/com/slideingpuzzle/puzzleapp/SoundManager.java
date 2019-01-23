package com.slideingpuzzle.puzzleapp;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.SoundPool;
import android.os.Build;

public class SoundManager {
    private SoundPool pool;
    private Context context;

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
        pool.play(soundID, 1, 1, 1, 0,1);
    }
    public void releaseSoundPool(){
        if (pool != null) {
            pool.release();
            pool = null;
        }
    }
}