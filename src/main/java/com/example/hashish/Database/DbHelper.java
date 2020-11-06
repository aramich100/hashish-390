package com.example.hashish.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.hashish.Database.DbConfig.COLUMN_COURSE_humidity;
import static com.example.hashish.Database.DbConfig.COLUMN_COURSE_light;
import static com.example.hashish.Database.DbConfig.COLUMN_COURSE_temperature;
import static com.example.hashish.Database.DbConfig.COLUMN_COURSE_water;
import static com.example.hashish.Database.DbConfig.DATABASE_NAME;
import static com.example.hashish.Database.DbConfig.TABLE_COURSE;

public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";


    private static final int DATABASE_VERSION = 1;

    private Context context = null;

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );

        this.context = context;

        Log.d(TAG, "DbHelper Constructor");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create our table
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_COURSE + "(" +
                COLUMN_COURSE_light + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COURSE_temperature + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COURSE_water + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_COURSE_humidity +"  INTEGER PRIMARY KEY AUTOINCREMENT)";

        Log.d(TAG, CREATE_TABLE_QUERY);

        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //whatever you want to ALTER
        //you will not use it
    }

    public long insertCourse(WeedClassInfo weedClassInfo)
    {
        if(weedClassInfo == null)  return -1;

        Log.d(TAG, "attempt to insert course: " + weedClassInfo.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        long id= -1;

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COURSE_light, weedClassInfo.getLight());
        contentValues.put(COLUMN_COURSE_temperature, weedClassInfo.getTemperature());
        contentValues.put(COLUMN_COURSE_water, weedClassInfo.getWater());
        contentValues.put(COLUMN_COURSE_humidity, weedClassInfo.getHumidity());
        try {
            id = db.insertOrThrow(TABLE_COURSE, null, contentValues);
            Log.d(TAG, "successfully inserted course");
        } catch(SQLException e) {
            Toast.makeText(context, "operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();

        } finally
        {
            db.close();
        }

        return id;


    }


    public List<WeedClassInfo> getAllCourses()
    {
        Log.d(TAG, "get all courses");
        SQLiteDatabase db = this.getReadableDatabase();

        List<WeedClassInfo> courseList = new ArrayList<>();

        Cursor cursor = null;
        try{

            cursor = db.query(TABLE_COURSE, null, null,
                    null, null,null,null);

            if(cursor != null)
            {
                cursor.moveToFirst();

                do{
                    int temperature = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_temperature));

                    int water = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_water));

                    int humidity = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_humidity));

                    int light = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_light));


                    courseList.add(new WeedClassInfo(light,water,humidity,temperature));
                } while(cursor.moveToNext());
            }
        }
        catch(Exception e){

        }
        finally
        {
            if(cursor != null)
                cursor.close();

            db.close();
        }

        return courseList;

    }

}
