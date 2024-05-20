package com.example.lab04_05;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_05.adapter.CourseAdapter;
import com.example.lab04_05.dao.CourseDAO;
import com.example.lab04_05.model.Course;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    // Cac bien thiet lap
    ListView listViewCourseList;

    ArrayList<Course> courseList = new ArrayList<>();

    CourseDAO courseDAO;

    CourseAdapter courseAdapter;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Lay id
        EditText courseID = findViewById(R.id.edtCourseID);
        EditText courseName = findViewById(R.id.edtCourseName);
        EditText releaseDate = findViewById(R.id.edtReleaseDate);
        EditText coursePrice = findViewById(R.id.edtCoursePrice);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);
        listViewCourseList = findViewById(R.id.lvCourseList);

        // Lay danh sach khoa hoc
        courseDAO = new CourseDAO(context);
        courseList = courseDAO.getListCourse();

        // Tao adapter tuy chinh va thiet lap cho listview
        courseAdapter = new CourseAdapter(context, courseList);
        listViewCourseList.setAdapter(courseAdapter);

        // Them su kien click cho listview
        listViewCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lay san pham dc chon tu listview
                Course course = courseList.get(position);

                // Dat du lieu cua course len tung edittext tuong ung
                courseID.setText(String.valueOf(course.getCourseID()));
                courseName.setText(String.valueOf(course.getCourseName()));
                // Set lai ngay
                String fullDate = course.getReleaseDate().toString(); // Giá trị: 2024-05-16 10:00:00
                String dateOnly = fullDate.substring(0, 10); // Chỉ lấy phần YYYY-MM-DD
                releaseDate.setText(dateOnly);
                coursePrice.setText(String.valueOf(course.getCoursePrice()));
            }
        });

        // Them khoa hoc muoi
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseID(courseID.getText().toString());
                course.setCourseName(courseName.getText().toString());
                String releaseDateString = releaseDate.getText().toString();
                Timestamp releaseDateT = course.formatStringToTimestampDateTime(releaseDateString);
                course.setReleaseDate(releaseDateT);
                BigDecimal coursePriceDec = new BigDecimal(coursePrice.getText().toString());
                course.setCoursePrice(coursePriceDec);

                long result = courseDAO.addCourse(course);

                if (result != -1) {
                    showAlert("Thông báo", "Thêm thành công");
                } else {
                    showAlert("Thông báo", "Thêm không thành công");
                }

                courseList.clear();
                courseList.addAll(courseDAO.getListCourse());
                courseAdapter.notifyDataSetChanged();
            }
        });

        // Chuc nang cap nhat thong tin khoa hoc
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseID(courseID.getText().toString());
                course.setCourseName(courseName.getText().toString());
                String releaseDateString = releaseDate.getText().toString();
                Timestamp releaseDateT = course.formatStringToTimestampDateTime(releaseDateString);
                course.setReleaseDate(releaseDateT);
                BigDecimal coursePriceDec = new BigDecimal(coursePrice.getText().toString());
                course.setCoursePrice(coursePriceDec);

                long result = courseDAO.updateCourse(course);

                if (result != 0) {
                    showAlert("Thông báo", "Cập nhật thành công");
                } else {
                    showAlert("Thông báo", "Cập nhật không thành công");
                }

                courseList.clear();
                courseList.addAll(courseDAO.getListCourse());
                courseAdapter.notifyDataSetChanged();
            }
        });

        // Chuc nang xoa thong tin khoa hoc
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xoa thong tin khoa hoc khoi csdl
                Course course = new Course();
                course.setCourseID(courseID.getText().toString());
                long result = courseDAO.deleteCourse(course);

                // Thong bao xoa thanh cong
                if (result != -1) {
                    showAlert("Thông báo", "Xóa thành công");
                } else {
                    showAlert("Thông báo", "Xóa không thành công");
                }

                courseList.clear();
                courseList.addAll(courseDAO.getListCourse());
                courseAdapter.notifyDataSetChanged();
            }
        });



    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

}
