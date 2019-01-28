package com.slideingpuzzle.puzzleapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class ScoresActivity extends AppCompatActivity {
    private String name,score;
    private TableLayout tableLayout;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        myDB =new DBHelper(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        score = intent.getStringExtra("score");
        tableLayout=findViewById(R.id.tableLayout);
        View tableRow1 = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
        TextView id  = (TextView) tableRow1.findViewById(R.id.rank);
        TextView name  = (TextView) tableRow1.findViewById(R.id.name);
        TextView level  = (TextView) tableRow1.findViewById(R.id.level);
        TextView time  = (TextView) tableRow1.findViewById(R.id.time);
        id.setText("Rank");
        name.setText("Name");
        level.setText("Level");
        time.setText("Time ");
        tableLayout.addView(tableRow1);
        Cursor cursor = myDB.getAllPlayers();
        if(cursor !=null){
            Log.i(" Feteched from db","================**** Player Ranking ****=========:");
            cursor.moveToFirst();
            int i=0;
            do{
                i++;
                String username =
                        cursor.getString(cursor.getColumnIndex(DBHelper.USERNAME_COL));
                String duration =
                        cursor.getString(cursor.getColumnIndex(DBHelper.DURATION_COL));
                String levelc =
                        cursor.getString(cursor.getColumnIndex(DBHelper.LEVEL_COL));
                String date =
                        cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL));
                String image =
                        cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_NAME_COL));
                View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
                TextView id1  = (TextView) tableRow.findViewById(R.id.rank);
                TextView name1  = (TextView) tableRow.findViewById(R.id.name);
                TextView level1  = (TextView) tableRow.findViewById(R.id.level);
                TextView time1  = (TextView) tableRow.findViewById(R.id.time);
                id1.setText(String.valueOf(i));
                name1.setText(username);
                level1.setText(String.valueOf(levelc));
                time1.setText(String.valueOf(duration));
                tableLayout.addView(tableRow);

            } while (cursor.moveToNext());
        }



    }
}
