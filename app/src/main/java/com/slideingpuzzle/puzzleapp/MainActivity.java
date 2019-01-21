package com.slideingpuzzle.puzzleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    //declaring button,spinner,editext
    private Button start;
    private EditText userName;
    private Spinner gameMode;
    private String gameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.next);
        userName =findViewById(R.id.username);
        gameMode=findViewById(R.id.game_mode);
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.game_mode, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gameMode.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),GameActivity.class);
//                String message = userName.getText().toString();
                gameText = gameMode.getSelectedItem().toString();
                Log.d("prefix","gameactivity"+gameText);
                intent.putExtra("prefix", gameText);
                startActivity(intent);

            }
        });
    }
}
