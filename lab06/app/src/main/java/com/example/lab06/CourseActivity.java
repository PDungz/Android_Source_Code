package com.example.lab06;

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

import com.example.lab06.adapter.CourseAdapter;
import com.example.lab06.dao.CourseDAO;
import com.example.lab06.model.Course;

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

        // Lay danh sach khoa hoc ra
        courseDAO = new CourseDAO(context);
        courseList = courseDAO.getListCourse();

        // Tao adapter va chuyen danh sach khoa hoc qua adapter de hien thi
        courseAdapter = new CourseAdapter(context, courseList);
        listViewCourseList.setAdapter(courseAdapter);

        // Them su kien click vao mot item trong listview
        listViewCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lay khoa hoc
                Course course = courseList.get(position);

                // Dua du lieu do len tung edt tuong ung
                courseID.setText(course.getCourseID());
                courseName.setText(course.getCourseName());
                // Set lai kieu ngay thang
                String fullDate = course.getReleaseDate().toString();
                String dateOnly = fullDate.substring(0,10);
                releaseDate.setText(dateOnly);
                coursePrice.setText(course.getCoursePrice().toString());
            }
        });

        // Them khoa hoc
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

                // Them khoa hoc moi
                long result = courseDAO.addCourse(course);

                if (result != -1) {
                    showAlert("Thông báo", "Thành công");
                } else {
                    showAlert("Thông báo", "Thất bại");
                }

                courseList.clear();
                courseList.addAll(courseDAO.getListCourse());
                courseAdapter.notifyDataSetChanged();
            }
        });

        // Cap nhat khoa hoc
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

                // Cap nhat khoa hoc moi
                long result = courseDAO.updateCourse(course);

                if (result > 0) {
                    showAlert("Thông báo", "Thành công");
                } else {
                    showAlert("Thông báo", "Thất bại");
                }

                courseList.clear();
                courseList.addAll(courseDAO.getListCourse());
                courseAdapter.notifyDataSetChanged();
            }
        });

        // Them khoa hoc
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseID(courseID.getText().toString());


                // Them khoa hoc moi
                long result = courseDAO.deleteCourse(course);

                if (result > 0) {
                    showAlert("Thông báo", "Thành công");
                } else {
                    showAlert("Thông báo", "Thất bại");
                }

                courseList.clear();
                courseList.addAll(courseDAO.getListCourse());
                courseAdapter.notifyDataSetChanged();
            }
        });


    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

}
