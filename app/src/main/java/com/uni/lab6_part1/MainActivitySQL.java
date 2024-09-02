package com.uni.lab6_part1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivitySQL extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editTextName, editTextAge;
    TextView textViewReadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_sql);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        textViewReadData = findViewById(R.id.textViewReadData);
        database = getBaseContext().openOrCreateDatabase("Lab6ataBase.db", MODE_PRIVATE, null);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        database.execSQL("CREATE TABLE IF NOT EXISTS USERS(NAME TEXT, AGE INTEGER)");
//        database.execSQL("INSERT OR IGNORE INTO USERS VALUES ('Vasia',23),('Petia',89)");
        // database.execSQL("DROP TABLE USERS");
    }

    public void writeToBD(View v) {
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());

        database.execSQL("INSERT INTO USERS (NAME, AGE) VALUES (?, ?)", new Object[]{name, age});
    }

    public void readFromDB(View v) {
        Cursor cursor = database.rawQuery("SELECT * FROM USERS", null);
        StringBuilder data = new StringBuilder();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            int age = cursor.getInt(cursor.getColumnIndex("AGE"));
            data.append("Name: ").append(name).append(", Age: ").append(age).append("\n");
        }

        textViewReadData.setText(data.toString());
        cursor.close(); // Закрываем курсор после использования
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null && database.isOpen()) {
            database.close(); // Закрываем базу данных
        }
    }
    public void clearData(View v) {
        database.execSQL("DELETE FROM USERS");
        textViewReadData.setText("");
    }
    public void updateData(View v) {
        String name = editTextName.getText().toString();
        int newAge = Integer.parseInt(editTextAge.getText().toString());

        database.execSQL("UPDATE USERS SET AGE = ? WHERE NAME = ?", new Object[]{newAge, name});
    }
    public void deleteData(View v) {
        String name = editTextName.getText().toString();

        database.execSQL("DELETE FROM USERS WHERE NAME = ?", new Object[]{name});
    }
}



