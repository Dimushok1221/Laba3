package com.dimushok.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import Data.DatabaseHandler;
import Utils.Util;

public class RecordsAct extends AppCompatActivity {

    DatabaseHandler dbHandler = new DatabaseHandler(this);
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        dbHandler= new DatabaseHandler(this);
        textView = findViewById(R.id.textView);

        addToTextView();
    }

    public void addToTextView(){
        // Получаем доступ к базе данных для чтения
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        // Выполняем запрос на получение всех записей из таблицы
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        // Подготавливаем строку для отображения записей
        StringBuilder recordsText = new StringBuilder();

        // Перебираем результаты запроса и добавляем их к строке
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(Util.KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(Util.KEY_NAME));
            String addTime = cursor.getString(cursor.getColumnIndex(Util.KEY_ADDTIME));
            // Здесь вам нужно использовать фактические имена столбцов в вашей таблице

            recordsText.append("id: ").append(id).append(", name: ").append(name).append(", addTime: ").append(addTime).append("\n");
        }

        // Закрываем курсор и базу данных
        cursor.close();
        db.close();

        // Отображаем записи в TextView
        textView.setText(recordsText.toString());
    }
}