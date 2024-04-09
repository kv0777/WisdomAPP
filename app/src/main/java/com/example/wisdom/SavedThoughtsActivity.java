package com.example.wisdom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SavedThoughtsActivity extends AppCompatActivity {

    private ListView savedThoughtsListView;
    private ArrayList<String> savedThoughtsList;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_thoughts);

        savedThoughtsListView = findViewById(R.id.savedThoughtsListView);
        backButton = findViewById(R.id.backButton);

        // Ініціалізуємо список збережених цитат
        savedThoughtsList = new ArrayList<>();

        // Завантаження збережених цитат з SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("SavedThoughts", MODE_PRIVATE);
        Set<String> savedThoughtsSet = sharedPreferences.getStringSet("savedThoughts", new HashSet<String>());

        // Очищення списку перед завантаженням нових цитат
        savedThoughtsList.clear();

        // Додавання збережених цитат до списку
        savedThoughtsList.addAll(savedThoughtsSet);

        // Ініціалізація та встановлення адаптера для списку
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, savedThoughtsList);
        savedThoughtsListView.setAdapter(adapter);

        // Додавання обробника подій для кнопки "Назад"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Завершуємо поточну активність і повертаємось до попередньої активності
            }
        });
    }
}
