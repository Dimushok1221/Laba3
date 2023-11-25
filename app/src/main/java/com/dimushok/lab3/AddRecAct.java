package com.dimushok.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Data.DatabaseHandler;
import Model.Student;
import Utils.Util;

public class AddRecAct extends AppCompatActivity {

    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    EditText textAddRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rec);

        textAddRec = findViewById(R.id.textAddRec);
    }

    public void add_row(View v){
        String text = textAddRec.getText().toString();
        databaseHandler.addStud(new Student(getLastId()+1, text, DatabaseHandler.getDate()));
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