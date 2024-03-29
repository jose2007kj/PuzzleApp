package com.slideingpuzzle.puzzleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declaring button,spinner,editext
    private Button level1,level2,level3;
    private EditText userName;
    private Spinner gameMode;
    private String gameText;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        level1 = findViewById(R.id.level1);
        level2=findViewById(R.id.level2);
        level3=findViewById(R.id.level3);
        userName =findViewById(R.id.username);
//        gameMode=findViewById(R.id.game_mode);
        // Create an ArrayAdapter using the string array and a default spinner layout

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.game_mode, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        gameMode.setAdapter(adapter);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectImageActivity.class);
//                String message = userName.getText().toString();
                playerName=userName.getText().toString();
                gameText = "Level 1 (2x2)";
                Log.d("prefix","gameactivity"+gameText);
                intent.putExtra("player_name", playerName);
                intent.putExtra("prefix", gameText);
                if(validate()){
                    startActivity(intent);
                }

            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectImageActivity.class);
//                String message = userName.getText().toString();
                playerName=userName.getText().toString();
                gameText = "Level 2 (3x3)";
                Log.d("prefix","gameactivity"+gameText);
                intent.putExtra("player_name", playerName);
                intent.putExtra("prefix", gameText);
                if(validate()){
                    startActivity(intent);
                }

            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectImageActivity.class);
//                String message = userName.getText().toString();
                playerName=userName.getText().toString();
                gameText = "Level 3 (4x4)";
                Log.d("prefix","gameactivity"+gameText);
                intent.putExtra("player_name", playerName);
                intent.putExtra("prefix", gameText);
                if(validate()){
                    startActivity(intent);
                }

            }
        });
    }

    public boolean validate() {
        boolean value = true;

        if(playerName.isEmpty()){
            userName.setError("Please Enter a user name");
            value = false;
        }
//        if(gameText.contains("Please select a game level")){
//            Toast.makeText(this, "Please select a game level", Toast.LENGTH_SHORT).show();
//            value = false;
//        }

        return value;
    }
}
