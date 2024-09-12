package com.example.cinenigma;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class DataDisplayActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        myDb = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);

        displayData();
    }

    private void displayData() {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // Show message if no data found
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("ID : ").append(res.getString(0)).append("\n");
            buffer.append("Titre : ").append(res.getString(1)).append("\n");
            buffer.append("Date : ").append(res.getString(2)).append("\n");
            buffer.append("Heure : ").append(res.getString(3)).append("\n");
            buffer.append("Note Scénario : ").append(res.getString(4)).append("\n");
            buffer.append("Note Réalisation : ").append(res.getString(5)).append("\n");
            buffer.append("Note Musique : ").append(res.getString(6)).append("\n");
            buffer.append("Critique : ").append(res.getString(7)).append("\n\n");
        }

        String[] data = buffer.toString().split("\n\n");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}
