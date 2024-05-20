package com.example.lab04_05.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab04_05.database.CourseDBHelper;
import com.example.lab04_05.model.Course;

import java.math.BigDecimal;
import java.sql.Timestamp; // Thêm import cho kiểu dữ liệu Timestamp
import java.util.ArrayList;

public class CourseDAO {
    private CourseDBHelper courseDB;
    private SQLiteDatabase sqLiteDatabase;

    public CourseDAO(Context context) {
        courseDB = new CourseDBHelper(context);
    }

    // Hàm đọc dữ liệu: READ (R trong CRUD)
    public ArrayList<Course> getListCourse() {
        ArrayList<Course> courseList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu ở chế độ chỉ đọc
            sqLiteDatabase = courseDB.getReadableDatabase();

            // Su dung thuc thi cau lenh SQL va tra ve tap du lieu truy van
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSE", null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Course course = new Course();
                    // Lấy các giá trị từ cursor và tạo đối tượng Course tương ứng
                    String courseID = cursor.getString(0);
                    String courseName = cursor.getString(1);
                    // Kiểm tra xem giá trị của cột releaseDate có null không trước khi chuyển đổi
                    String releaseDateString = cursor.getString(2);
                    Timestamp releaseDate = course.formatStringToTimestampDate(releaseDateString);
                    BigDecimal coursePrice = new BigDecimal(cursor.getString(3));
                    // Tạo đối tượng Course và thêm vào danh sách
                    course.setCourseID(courseID);
                    course.setCourseName(courseName);
                    course.setReleaseDate(releaseDate);
                    course.setCoursePrice(coursePrice);
                    // Gan vao danh sach
                    courseList.add(course);
                } while (cursor.moveToNext());
            }
            cursor.close(); // Đóng cursor sau khi sử dụng xong
        } catch (Exception e) {
            Log.e("CourseDAO", "Error while getting course list: " + e.getMessage());
        } finally {
            // Đóng kết nối đến cơ sở dữ liệu
//            if (sqLiteDatabase != null) {
//                sqLiteDatabase.close();
//            }
        }
        return courseList;
    }

    // Ham them: CREATE (C trong CRUD)
    public long addCourse(Course course) {
        long checkInsert = -1;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("courseID", course.getCourseID());
            contentValues.put("courseName", course.getCourseName());
            contentValues.put("releaseDate", course.getReleaseDate().toString());
            contentValues.put("coursePrice", course.getCoursePrice().toString());

            checkInsert = sqLiteDatabase.insert("COURSE", null, contentValues);
        } catch (Exception e) {
            Log.e("CourseDAO", "Error while getting course list: " + e.getMessage());
        }
        return checkInsert;
    }

    // Ham xoa: DELETE (D trong CRUD)
    public long deleteCourse(Course course) {
        long checkDelete = -1;
        try {
            checkDelete = sqLiteDatabase.delete("COURSE", "courseID=?", new String[]{String.valueOf(course.getCourseID())});
        } catch (Exception e) {
            Log.e("TAG", "Error: " + e.getMessage());
        }
        return checkDelete;
    }

    // Ham update: UPDATE (U trong CRUD)
    public long updateCourse(Course course) {
        long checkUpdate = -1;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("courseID", course.getCourseID());
            contentValues.put("courseName", course.getCourseName());
            contentValues.put("releaseDate", course.getReleaseDate().toString());
            contentValues.put("coursePrice", course.getCoursePrice().toString());

            checkUpdate = sqLiteDatabase.update("COURSE", contentValues, "courseID=?", new String[]{String.valueOf(course.getCourseID())});
        } catch (Exception e) {
            Log.e("TAG", "Error: " + e.getMessage());
        }
         return checkUpdate;
    }
}
