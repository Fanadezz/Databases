package com.androidshowtime.databases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

       /* sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Nick',28)");
        sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Tonnie',33)");
        sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('GOAT',13)");*/


        //select all names beginning with G
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE 'G%'",
                                           null);

        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Timber.i("Name is %s: and Age is: %s", c.getString(nameIndex), c.getInt(ageIndex));

            c.moveToNext();

        }

        c.close();
    }
}