package com.slideingpuzzle.puzzleapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private int NO_OF_PIECES;
    private String BUTTON_NAME_PREFIX;
    int time_to_solve_puzzle = 1;
    private String playerName;
    private String gameLevel;
    private SoundManager soundManager;
    private int congratsSound;
    private String imageName;

    //an array of buttons
    Button[] btn;

    //correct sequence of IDs of buttons
    int[] correct_id_seq;

    /*this array is the working array.
      Its element's values are similar to correct_id_seq[] except diff locations*/
    int[] rand_id_seq;

    //array to keep 2 indexes of 2 elements in the array rand_id_seq from 2 clicks
    int two_indexes_to_swap_img[] = {-1, -1};

    int num_of_clicks = 0; //need 2 clicks to swap images

    Button two_buttons_to_swap[] = {null, null};

    TextView timeTextView;

    Timer T;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        gameLevel = intent.getStringExtra("prefix");
        playerName = intent.getStringExtra("player_name");
        imageName=intent.getStringExtra("image_prefix");
        myDB =new DBHelper(this);
        setContentView(R.layout.activity_game);
        soundManager = new SoundManager(this);
        congratsSound = soundManager.addSound(R.raw.congrats);
        //the below code for a variavle for storing dynamic layout
        LinearLayout dynamicContent = (LinearLayout) findViewById(R.id.dynamic_content);
        Log.d("prefix","prefix is+"+gameLevel);
        //dynamically adding 3 diifrent layouts for 3 levels,also setting btun prefix,no.of.buttons
        switch(gameLevel){
            case "Level 1 (2x2)":
                NO_OF_PIECES = 4;
                BUTTON_NAME_PREFIX = "btn";
//                imageName = "g2x2c";
                View wizardView = getLayoutInflater()
                        .inflate(R.layout.levelone, dynamicContent, false);
                dynamicContent.addView(wizardView);
                break;
            case "Level 2 (3x3)":
                NO_OF_PIECES = 9;
                BUTTON_NAME_PREFIX = "l2btn";
//                imageName = "g3x3c";
                View level2 = getLayoutInflater()
                        .inflate(R.layout.leveltwo, dynamicContent, false);
                dynamicContent.addView(level2);
                break;
            default:
                NO_OF_PIECES = 16;
                BUTTON_NAME_PREFIX = "l3btn";
//                imageName = "c";
                View level3 = getLayoutInflater()
                        .inflate(R.layout.levelthree, dynamicContent, false);
                dynamicContent.addView(level3);

        }
        timeTextView = (TextView) findViewById(R.id.timetextView);
        btn = new Button[NO_OF_PIECES];
        correct_id_seq = new int[NO_OF_PIECES];
        rand_id_seq = new int[NO_OF_PIECES];

        // get buttons and store in an button array
        for(int i = 0; i < NO_OF_PIECES; i++) {
            Log.d("id","button prefix is"+BUTTON_NAME_PREFIX);
            Log.d("id","resources :"+this.getResources().getIdentifier(BUTTON_NAME_PREFIX + Integer.toString(i),"id", this.getPackageName()));

            btn[i] = (Button) findViewById(this.getResources().getIdentifier(
                    BUTTON_NAME_PREFIX + Integer.toString(i), "id", this.getPackageName()));
        }
        play_game(10, imageName);
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
    //code for share button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    //code for share buttton
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //code for sharing the app

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Hai i loved the puzzleApp its really amazing you too try";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share PuzzleApp to your friends"));
        //ends here

        if (id == R.id.action_name) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //function make array elements appear random
    public void rand_arr_elements(int[] arr) {
        Random random = new Random();
        int temp_index;
        int temp_obj;
        for (int i = 0; i < arr.length - 1; i++) {
            //a random number between i+1 and arr.length - 1
            temp_index = i + 1 + random.nextInt(arr.length - 1 - i);
            //swap the element at i with the element at temp_index
            temp_obj = arr[i];
            arr[i] = arr[temp_index];
            arr[temp_index] = temp_obj;
        }
    }
    public void play_game(int perusal_time_by_seconds, String image_name) {

        //set the values for the correct_id_seq array
        for(int i = 0; i < NO_OF_PIECES; i++){
            correct_id_seq[i] = this.getResources().getIdentifier(image_name
                    + Integer.toString(i),"drawable", this.getPackageName());
        }

        // based on the values of correct_id_seq, set the button background
        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i].setBackgroundResource(correct_id_seq[i]);
        }

        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i].setClickable(false);
        }

        //display image in an amount of perusal_time_by_seconds
        new CountDownTimer(perusal_time_by_seconds * 1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText("TIME: " + Long.toString(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {

                for(int i = 0; i < NO_OF_PIECES; i++){
                    btn[i].setClickable(true);
                }
                make_puzzle_with_time_tick();
            }
        }.start();
    }


    public void make_puzzle_with_time_tick(){
        //construct rand_id_seq array, firstly, start with the correct sequence of ids
        rand_id_seq = Arrays.copyOf(correct_id_seq, correct_id_seq.length);

        //and then call the function rand_arr_elements to randomly swap elements
        //call 2 times for better results
        rand_arr_elements(rand_id_seq); rand_arr_elements(rand_id_seq);

        // based on the values of rand_id_seq, set the buttons' background
        for(int i = 0; i < NO_OF_PIECES; i++){
            btn[i].setBackgroundResource(rand_id_seq[i]);
        }

        //counting time by seconds
        time_to_solve_puzzle = -1;
        T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time_to_solve_puzzle++;
                        timeTextView.setText("TIME: " + time_to_solve_puzzle);
                    }
                });
            }
        }, 1000, 1000);
    }


    public void on_click_image(View v){
        Button button = (Button)v;

        //value of temp similar to com.jc165984.puzzle:id/btn0
        String temp_str = button.getResources().getResourceName(button.getId());

        // "id/" + BUTTON_NAME_PREFIX = "id/btn"
        int id_pos = temp_str.indexOf("id/" + BUTTON_NAME_PREFIX);

        //get the button's index.For example, id/btn0 has index 0
        int index = Integer.parseInt(temp_str.substring(id_pos +
                ("id/" + BUTTON_NAME_PREFIX).length()));

        two_indexes_to_swap_img[num_of_clicks] = index;
        two_buttons_to_swap[num_of_clicks] = button;

        if (num_of_clicks == 1) {
            //2 clicks already - swap images now
            two_buttons_to_swap[0].setBackgroundResource(rand_id_seq[two_indexes_to_swap_img[1]]);
            two_buttons_to_swap[1].setBackgroundResource(rand_id_seq[two_indexes_to_swap_img[0]]);
            //update the rand_id_seq array
            int temp = rand_id_seq[two_indexes_to_swap_img[0]];
            rand_id_seq[two_indexes_to_swap_img[0]] = rand_id_seq[two_indexes_to_swap_img[1]];
            rand_id_seq[two_indexes_to_swap_img[1]] = temp;

            //check if the 2 array rand_id_seq and correct_id_seq are equal
            //if it is then the user wins
            if (Arrays.equals(correct_id_seq, rand_id_seq)) {
                if (T != null)
                    T.cancel();
                Log.i("Time = ", Integer.toString(time_to_solve_puzzle));
                for(int i = 0; i < NO_OF_PIECES; i++){
                    btn[i].setClickable(false);
                }
                soundManager.play(congratsSound);
                //below code for inserting into db
                switch(gameLevel){
                    case "Level 1 (2x2)":
                        myDB.insertPlayer(playerName, time_to_solve_puzzle , 1,"g2x2c");
                        break;
                    case "Level 2 (3x3)":
                        myDB.insertPlayer(playerName, time_to_solve_puzzle , 2,"g3x3c");
                        break;
                    default:
                        myDB.insertPlayer(playerName, time_to_solve_puzzle , 3,"c");

                }
                //below code for printing db details
                Cursor cursor = myDB.getAllPlayers();
                if(cursor !=null){
                    Log.i(" Feteched from db","================**** Player Ranking ****=========:");
                    cursor.moveToFirst();
                    do{
                        String username =
                                cursor.getString(cursor.getColumnIndex(DBHelper.USERNAME_COL));
                        String duration =
                                cursor.getString(cursor.getColumnIndex(DBHelper.DURATION_COL));
                        String level =
                                cursor.getString(cursor.getColumnIndex(DBHelper.LEVEL_COL));
                        String date =
                                cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL));
                        String image =
                                cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_NAME_COL));

                        Log.i(" Feteched from db","========================:");
                        Log.d("Player:","Player:"+username);
                        Log.d("Time took:","Time took:"+duration);
                        Log.d("Game Level:","Game Level:"+level);
                        Log.d("Image Name:","Image Name is:"+image);
                        Log.i(" Feteched from db","========================:");
                    } while (cursor.moveToNext());
                }
            }
        }
        num_of_clicks++;

        if (num_of_clicks == 2)
            num_of_clicks = 0;
    }


}
