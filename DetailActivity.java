package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import com.example.final_project.DetailViewModel;
import com.example.firebase.R;
import android.app.Activity;

public class DetailActivity extends Activity {
    private DetailViewModel detailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // استيراد الحزم الأخرى وتعريف المتغيرات المطلوبة

        // تهيئة المتغيرات والعناصر في واجهة المستخدم

        detailViewModel = new ViewModelProvider((ViewModelStoreOwner) DetailActivity.this).get(DetailViewModel.class);

    }
}
