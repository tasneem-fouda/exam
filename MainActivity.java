package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.MainViewModel;
import com.example.final_project.MyAdapter;
import com.example.firebase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class MainActivity extends Activity {
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private EditText searchEditText;
    private SearchView search;

    private ImageButton searchButton;
    private String searchText = "";
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();

        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(MainActivity.this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        search = findViewById(R.id.search);


//
//        searchEditText = findViewById(R.id.searchEditText);
//        searchButton = findViewById(R.id.searchButton);

        // استدعاء دالة لجلب البيانات من Firebase
        mainViewModel.retrieveData().observe((LifecycleOwner) MainActivity.this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> data) {
                dataList = data;
                List<String> filteredData = filterData(data);

                adapter.setData(filteredData);
            }
        });

        // استدعاء دالة للبحث عن البيانات عند النقر على زر البحث
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText = searchEditText.getText().toString();
                mainViewModel.searchData(searchText);
            }
        });

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // استدعاء دالة لإضافة بيانات جديدة إلى Firebase
                mainViewModel.addData("dataKey", "New Data");
            }
        });
        adapter.setOnItemClickListener((MyAdapter.OnItemClickListener) MainActivity.this);
    }
    public void onItemClick(int position) {
        // استدعاء الدالة لعرض تفاصيل العنصر
        showItemDetails(position);
    }
    private void showItemDetails(int position) {
        // استخراج العنصر المحدد من قائمة البيانات
        String selectedItem = dataList.get(position);

        // عرض تفاصيل العنصر (يمكنك استخدام Toast أو Dialog أو تحويل إلى شاشة تفاصيل جديدة)
        Toast.makeText(MainActivity.this, "تفاصيل العنصر: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    private List<String> filterData(List<String> data) {
        List<String> filteredData = new ArrayList<>();
        if (TextUtils.isEmpty(searchText)) {
            filteredData.addAll(data);
        } else {
            for (String item : data) {
                if (item.contains(searchText)) {
                    filteredData.add(item);
                }
            }
        }
        return filteredData;
    }
}
