package com.zjy.daydayup.LayoutManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.zjy.daydayup.R;

import java.util.ArrayList;

public class LayoutManagerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager_test);

        RecyclerView rvTest = findViewById(R.id.rv_test);
        TestAdapter testAdapter = new TestAdapter(this);
        FirstLayoutManager firstLayoutManager = new FirstLayoutManager();
        rvTest.setLayoutManager(firstLayoutManager);
        rvTest.setAdapter(testAdapter);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<100;i++){
            list.add(Color.RED);
            list.add(Color.GRAY);
            list.add(Color.GREEN);
        }
        testAdapter.addRefreshData(list);
    }
}