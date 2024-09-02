package com.uni.lab6_part1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivityExter extends AppCompatActivity {
    EditText editTextExt;
    TextView textViewEx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_exter);
        editTextExt = findViewById(R.id.editTextExt);
        textViewEx = findViewById(R.id.textViewEx);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void writeExternal(View v) throws Exception{
        File file = new File(getExternalFilesDir(null), "lab6_external.js");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(editTextExt.getText().toString().getBytes(StandardCharsets.UTF_8));
        fos.flush();
    }

    public void readExternal(View v)throws Exception{
        File file = new File(getExternalFilesDir(null), "lab6_external.js");
        FileInputStream fin = new FileInputStream(file);
        byte[] bytes = new byte[fin.available()];
        fin.read(bytes);
        String text = new String(bytes);
        textViewEx.setText(text);
        Log.i("External", text);
    }
}