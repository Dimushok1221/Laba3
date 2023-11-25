package com.dimushok.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import Data.DatabaseHandler;
import Utils.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    DatabaseHandler databaseHandler = new DatabaseHandler(this);

    /*public void addToData(){
        databaseHandler.ClearTable();
        databaseHandler.addStud(new Student("Баркалов ИЛья Александрович", "01.02.2022"));
        databaseHandler.addStud(new Student("Гришанов Богдан Евгеньевич", "02.02.2022"));
        databaseHandler.addStud(new Student("Кошелев Дмитрий Юрьевич", "03.02.2022"));
        databaseHandler.addStud(new Student("Свиридов Иван", "04.02.2022"));
        databaseHandler.addStud(new Student("Золотарев Александр Владиславович", "05.02.2022"));
    }*/

    public void go_to_act_2(View v){
        Intent intent = new Intent(this, RecordsAct.class);
        startActivity(intent);
    }

    public void go_to_actAddRec(View v){
        Intent intent = new Intent(this, AddRecAct.class);
        startActivity(intent);
    }

    public void change_last_row(View v){
        databaseHandler.updStud(getLastId(), "Иванов Иван Иванович", DatabaseHandler.getDate());
    }

    public int getLastId(){
        String query = "SELECT MAX(" + Util.KEY_ID + ") FROM " + Util.TABLE_NAME + "; ";
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            int lastInsertedId = cursor.getInt(0);
            cursor.close();
            return lastInsertedId;
        }

        db.close();
        return 0;
    }
}