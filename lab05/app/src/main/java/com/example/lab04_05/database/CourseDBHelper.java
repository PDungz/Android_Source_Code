package com.example.lab04_05.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "COURSE_DB";
    private static final int DB_VERSION = 5;

    public CourseDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Phương thức này được gọi khi cơ sở dữ liệu được tạo lần đầu tiên
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Lệnh SQL để tạo bảng dữ liệu
        String create_table_sqlite = "CREATE TABLE COURSE (" +
                "courseID TEXT PRIMARY KEY," +
                "courseName TEXT," +
                "releaseDate DATETIME," + // Thay đổi kiểu dữ liệu của cột releaseDate thành TEXT
                "coursePrice DECIMAL)"; // Thêm kiểu dữ liệu cho cột coursePrice

        // Thực thi lệnh để tạo bảng
        sqLiteDatabase.execSQL(create_table_sqlite);

        // Thêm dữ liệu mẫu vào bảng
        String insert_table_sqlite = "INSERT INTO COURSE(courseID, courseName, releaseDate, coursePrice) VALUES ('FGM01', 'Figma', '2024-05-16 00:00:00', 10.0)";
        sqLiteDatabase.execSQL(insert_table_sqlite);
    }

    // Phương thức này được gọi khi cần nâng cấp cơ sở dữ liệu
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Kiểm tra nếu phiên bản cũ không phù hợp với phiên bản mới, thì xóa bảng cũ và tạo lại
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS COURSE");
            onCreate(sqLiteDatabase);
        }
    }
}
