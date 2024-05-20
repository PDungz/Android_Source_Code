package com.example.lab06.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "COURSE_DB";
    private static final int DB_VERSION = 1;

    public  CourseDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION)   ;
    }

    // Tao bang trong csdl
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Cau lenh tao bang
        String create_table_sqlite = "CREATE TABLE COURSE (" +
                "courseID TEXT PRIMARY KEY," +
                "courseName TEXT," +
                "releaseDate DATETIME," +
                "coursePrice DECIMAL)";
        // Thuc hien lenh tao bang
        sqLiteDatabase.execSQL(create_table_sqlite);

        // Them du lieu mau vao bang vua tao
        String insert_table_sqlite = "INSERT INTO COURSE(courseID, courseName, releaseDate, coursePrice) VALUES ('FGM01', 'Figma', '2024-05-16 00:00:00', 10.0)";
        sqLiteDatabase.execSQL(insert_table_sqlite);
    }

    // Cap nhat lai csdl
    @Override
    public  void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Kiem tra version duoc nang new > cu
        if(oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS COURSE");
            onCreate(sqLiteDatabase);
        }
    }
}
