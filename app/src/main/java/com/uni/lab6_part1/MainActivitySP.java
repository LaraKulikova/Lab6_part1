package com.uni.lab6_part1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivitySP extends AppCompatActivity {

    EditText editTextSP;
    TextView textViewSP;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_sp);

        editTextSP = findViewById(R.id.editTextExt);
        textViewSP = findViewById(R.id.textViewSP);
        settings = getSharedPreferences("SharedPrefFile", MODE_PRIVATE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void writeSP(View v) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("key", editTextSP.getText().toString());
        editor.apply();
      //  editTextSP.setText("");
    }

    public void readSP(View v) {
        String temp = settings.getString("key", "значение по умолчанию");
        textViewSP.setText(temp);
    }

    public void returnMainPage(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}