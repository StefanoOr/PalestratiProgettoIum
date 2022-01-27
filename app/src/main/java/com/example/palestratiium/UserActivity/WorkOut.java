package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.palestratiium.R;

public class WorkOut extends AppCompatActivity {
    public static final String EXTRA_USER = "package com.example.palestratiium";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);
    }
}