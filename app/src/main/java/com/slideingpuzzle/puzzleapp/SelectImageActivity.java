package com.slideingpuzzle.puzzleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectImageActivity extends AppCompatActivity {
    private TextView title;
    private Button btn1,btn2,btn3;
    private String gameLevel;
    private String playerName;
    private int backgroundSong;
    private SoundManager soundManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        btn1=findViewById(R.id.selectbtn0);
        btn2=findViewById(R.id.selectbtn1);
        btn3=findViewById(R.id.selectbtn2);
        title=findViewById(R.id.textView);
        soundManager = new SoundManager(this);

        Intent intent = getIntent();
        gameLevel = intent.getStringExtra("prefix");
        playerName = intent.getStringExtra("player_name");
        backgroundSong = soundManager.addSound(R.raw.backsong);
        soundManager.play(backgroundSong);
        switch(gameLevel){
            case "Level 1 (2x2)":
                title.setText("LEVEL 1");
                btn1.setBackgroundResource(R.drawable.levelone);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","gtwo2x2c" );

                        startActivity(intent);

                    }
                });
                btn2.setBackgroundResource(R.drawable.levelonetwo);//2dogs
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","gone2x2c" );

                        startActivity(intent);

                    }
                });
                btn3.setBackgroundResource(R.drawable.leveloneoneone);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","g2x2c" );

                        startActivity(intent);

                    }
                });
//                NO_OF_PIECES = 4;
//                BUTTON_NAME_PREFIX = "btn";
//                imageName = "g2x2c";
//                View wizardView = getLayoutInflater()
//                        .inflate(R.layout.levelone, dynamicContent, false);
//                dynamicContent.addView(wizardView);
                break;
            case "Level 2 (3x3)":
                title.setText("LEVEL 2");
                btn1.setBackgroundResource(R.drawable.alexgeorge);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","g3x3c" );

                        startActivity(intent);

                    }
                });
                btn2.setBackgroundResource(R.drawable.leveltwothree);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","gone3x3c" );

                        startActivity(intent);

                    }
                });
                btn3.setBackgroundResource(R.drawable.leveltwotwo);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","gtwo3x3c" );

                        startActivity(intent);

                    }
                });
//                NO_OF_PIECES = 9;
//                BUTTON_NAME_PREFIX = "l2btn";
//                imageName = "g3x3c";
//                View level2 = getLayoutInflater()
//                        .inflate(R.layout.leveltwo, dynamicContent, false);
//                dynamicContent.addView(level2);
                break;
            default:
                btn1.setBackgroundResource(R.drawable.ghghghg);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","onec" );

                        startActivity(intent);

                    }
                });
                btn2.setBackgroundResource(R.drawable.gytyt);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","twoc" );

                        startActivity(intent);

                    }
                });
                btn3.setBackgroundResource(R.drawable.c);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                       String message = userName.getText().toString();

                        intent.putExtra("player_name", playerName);
                        intent.putExtra("prefix", gameLevel);
                        intent.putExtra("image_prefix","c" );

                        startActivity(intent);

                    }
                });
                title.setText("LEVEL 3");

        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        soundManager.resume();
//        congratsSound = soundManager.addSound(R.raw.congrats);
    }

    protected void onPause(){
        super.onPause();
        soundManager.pause();
    }
    protected void onDestroy(){
        super.onDestroy();
        soundManager.releaseSoundPool();
    }
}
