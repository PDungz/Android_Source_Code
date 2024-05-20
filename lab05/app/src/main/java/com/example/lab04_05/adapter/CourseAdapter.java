package com.example.lab04_05.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab04_05.R;
import com.example.lab04_05.model.Course;

import java.util.ArrayList;
import java.util.Objects;

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Course> courseList;

    public CourseAdapter(Context context, ArrayList<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview, null);
        }
        // Anh xa
        TextView courseID = view.findViewById(R.id.tvCourseID);
        TextView courseName = view.findViewById(R.id.tvCourseName);
        TextView releaseDate = view.findViewById(R.id.tvReleaseDate);
        TextView coursePrice = view.findViewById(R.id.tvCoursePrice);

        // lay doi tuong course
        Course course = courseList.get(position);

        // Thiet lap cac thanh phan trong item
        courseID.setText(course.getCourseID());
        courseName.setText(course.getCourseName());
        // Set lai ngay
        String fullDate = course.getReleaseDate().toString(); // Giá trị: 2024-05-16 10:00:00
        String dateOnly = fullDate.substring(0, 10); // Chỉ lấy phần YYYY-MM-DD
        releaseDate.setText(dateOnly);
        coursePrice.setText(course.getCoursePrice().toString());

        return  view;
    }
}
