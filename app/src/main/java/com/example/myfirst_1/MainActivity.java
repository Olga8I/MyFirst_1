package com.example.myfirst_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myfirst_1.adapter.CategoryAdapter;
import com.example.myfirst_1.adapter.CourseAdapter;
import com.example.myfirst_1.model.Category;
import com.example.myfirst_1.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Игры"));
        categoryList.add(new Category(2, "Сайты"));
        categoryList.add(new Category(3, "Языки"));
        categoryList.add(new Category(4, "Прочее"));

        setCategoryRecycler(categoryList);


        courseList.add(new Course(1, "java", "Професия Java\n Разработчик", "1 января","начальный", "#FFFAFA", "Test", 3 ));
        courseList.add(new Course(2, "python", "Професия Python\n Разработчик", "6 января", "начальный", "#87CEFA", "Test", 3));
        courseList.add(new Course(3, "javascript", "Професия JS\n Разработчик", "12 января","начальный", "#FFFF00", "Test", 2));

        fullCourseList.addAll(courseList);
        setCourseRecycler(courseList);
}
    public void openShopingCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }
    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(LayoutManager);

        courseAdapter = new CourseAdapter(this,courseList);
        courseRecycler.setAdapter(courseAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(LayoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullCourseList);
        List<Course> filterCourses = new ArrayList<>();
        for(Course c : courseList){
            if(c.getCategory() == category)
                filterCourses.add(c);
        }
        courseList.clear();
        courseList.addAll(filterCourses);
        courseAdapter.notifyDataSetChanged();

    }
}