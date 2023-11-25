package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Model.Student;
import Utils.Util;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_ADDTIME + " TEXT" + " )";

        sqLiteDatabase.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStud(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_ADDTIME, student.getAddTime());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updStud(int id, String name, String addTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, name);
        contentValues.put(Util.KEY_ADDTIME, addTime);

        db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void ClearTable()
    {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, null, null);
    }

    public Student getStud(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_ADDTIME},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return student;
    }

    public List<Student> getAllStud () {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student> studList = new ArrayList<>();
        String selectAllStud = "Select * from " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStud, null);
        if (cursor.moveToFirst()){
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString((1)));
                student.setAddTime(cursor.getString(2));

                studList.add(student);
            } while(cursor.moveToNext());
        }
        return studList;
    }

    public static String getDate(){
        Date currentDate = Calendar.getInstance().getTime();

        // Форматирование даты в строку
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
}
