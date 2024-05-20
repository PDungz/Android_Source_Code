package com.example.lab06.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab06.R;
import com.example.lab06.model.Course;

import java.util.ArrayList;

public class CourseAdapter extends BaseAdapter {
    // Bien cau hinh
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview, null);
        }

        // Lay id ra
        TextView courseID = view.findViewById(R.id.tvCourseID);
        TextView courseName = view.findViewById(R.id.tvCourseName);
        TextView releaseDate = view.findViewById(R.id.tvReleaseDate);
        TextView coursePrice = view.findViewById(R.id.tvCoursePrice);

        // Lay doi tuong course
        Course course = courseList.get(position);

        // Thiet lap cac gia tri
        courseID.setText(course.getCourseID());
        courseName.setText(course.getCourseName());

        // Set lai kieu ngay thang
        String fullDate = course.getReleaseDate().toString();
        String dateOnly = fullDate.substring(0,10);
        releaseDate.setText(dateOnly);
        coursePrice.setText(course.getCoursePrice().toString());
        return  view;
    }

}
