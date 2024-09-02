package com.uni.lab6_part1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivityFile2 extends AppCompatActivity {
    EditText editTextFile;
    TextView textViewFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_file2);
        editTextFile = findViewById(R.id.editTextFile);
        textViewFile = findViewById(R.id.textViewFile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void writeFile(View v) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("lab6file.txt", MODE_PRIVATE);
            String textToWrite = editTextFile.getText().toString(); // Получаем текст из EditText
            fos.write(textToWrite.getBytes()); // Записываем текст в файл
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения
        } finally {
            if (fos != null) {
                try {
                    fos.close(); // Закрываем поток
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readFile(View v) throws IOException {
        FileInputStream fin = openFileInput("lab6file.txt");
        byte[] bytes = new byte[fin.available()];
       fin.read(bytes);
       String text = new String(bytes);
        textViewFile.setText(text);
    }
}