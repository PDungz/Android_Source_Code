package com.example.lab06.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab06.database.CourseDBHelper;
import com.example.lab06.model.Course;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;

public class CourseDAO {
    // Bien cau hinh
    private CourseDBHelper courseDB;
    private SQLiteDatabase sqLiteDatabase;

    public CourseDAO(Context context) {
        courseDB = new CourseDBHelper(context);
    }

    // Ham: Doc du lieu
    public ArrayList<Course> getListCourse() {
        ArrayList<Course> listCourse = new ArrayList<>();
        // Mo ket noi csld o che do doc
        sqLiteDatabase = courseDB.getReadableDatabase();
        try {

            // Su dung cau lenh sql de tra ve du lieu truy van
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSE", null);

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    //
                    Course course = new Course();
                    // cac gia tri ra
                    String courseID = cursor.getString(0);
                    String courseName = cursor.getString(1);
                    // Kieu du lieu ngay phat hanh
                    String releaseDateString = cursor.getString(2);
                    Timestamp releaseDate = course.formatStringToTimestampDate(releaseDateString);
                    // Gia tien
                    BigDecimal coursePrice = new BigDecimal(cursor.getString(3));
                    // Gan gia tri cho cac thuoc tinh cua doi tuong khoa hoc
                    course.setCourseID(courseID);
                    course.setCourseName(courseName);
                    course.setReleaseDate(releaseDate);
                    course.setCoursePrice(coursePrice);

                    // Gan vao danh khoa hoc
                    listCourse.add(course);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return listCourse;
    }

    // Ham: Them du lieu
    public long addCourse(Course course) {
        long checkInsert = -1;
        // Mo ket noi csld o che do doc
        sqLiteDatabase = courseDB.getReadableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("courseID", course.getCourseID());
            contentValues.put("courseName", course.getCourseName());
            contentValues.put("releaseDate", course.getReleaseDate().toString());
            contentValues.put("coursePrice", course.getCoursePrice().toString());

            checkInsert = sqLiteDatabase.insert("COURSE", null, contentValues);

        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkInsert;
    }

    // Ham: Cap nhat du lieu
    public long updateCourse(Course course) {
        long checkUpdate = -1;
        // Mo ket noi csld o che do doc
        sqLiteDatabase = courseDB.getReadableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("courseID", course.getCourseID());
            contentValues.put("courseName", course.getCourseName());
            contentValues.put("releaseDate", course.getReleaseDate().toString());
            contentValues.put("coursePrice", course.getCoursePrice().toString());

            checkUpdate = sqLiteDatabase.update("COURSE", contentValues, "courseID=?", new String[]{String.valueOf(course.getCourseID())});

        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkUpdate;
    }

    // Ham: Xoa du lieu
    public long deleteCourse(Course course) {
        long checkDelete = -1;
        // Mo ket noi csld o che do doc
        sqLiteDatabase = courseDB.getReadableDatabase();
        try {
            checkDelete = sqLiteDatabase.delete("COURSE", "courseID=?", new String[]{String.valueOf(course.getCourseID())});

        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return checkDelete;
    }
}
