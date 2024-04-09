package com.example.wisdom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView thoughtTextView;
    private Button nextButton;
    private Button saveButton;
    private Button savedThoughtsButton;
    private Button famousPeopleButton;

    private String[] thoughts = {
            "Життя – це те, що з тобою відбувається, поки ти зайнятий планами.",
            "Життя – це те, що трапляється тобі під час того, як ти зайнятий планами.",
            "Життя – це те, що з тобою трапляється, поки ти робиш інші плани.",
            "Життя – це те, що з тобою трапляється, поки ти стараєшся влаштуватися з іншими планами.",
            "Щоб зрозуміти справжню цінність хвилини, поспішай з тим, хто пропадає, а не з тим, хто з тобою.",
            "Три речі в житті ніколи не повертаються: час, слово, можливість.",
            "Той, хто втрачає час, шкодує про це до кінця життя.",
            "Життя занадто коротке, щоб витрачати час на образи й звинувачення.",
            "Не гаю часу на образи. Мені ніколи не вистачало їх. Час, який витрачено на образи, втрачено часу.",
            "Найцінніші в житті – це час, який ти витрачаєш на те, що любиш, і на тих, хто тебе любить.",
            "Найбільше гріха – це втрачений час.",
            "Час – це гроші.",
            "Витрачайте час на те, що принесе вам щастя.",
            "Життя є, коли вишиваєш його з мрій.",
            "Відгук – це час, який витрачено на добру справу.",
            "Краще бути ніщо за величезним часом, якщо нічого вибрати.",
            "Нехай ваші мрії будуть більшими, ніж ваші страхи, а дії – сильнішими, ніж ваші слова.",
            "Час, який ви витрачаєте на друзів, – це час, який ви витрачаєте на власну майбутню сім'ю.",
            "Час – це валюта, яку ви витрачаєте на свої мрії.",
            "Життя – це те, що трапляється з тобою, коли ти зайнятий планами.",
            "Лише той, хто витрачає час на плани, може бачити майбутнє.",
            "Час – це все.",
            "Краще витратити час на розвиток, ніж просто прожити його.",
            "Час – це валюта, яку ви витрачаєте на свої мрії.",
            "Час – це валюта, якою можна купити все.",
            "Життя – це коротке, але дуже цінне.",
            "Час – це деньги, і ви повинні витрачати його розумно.",
            "Витрачайте час на те, що принесе вам щастя.",
            "Життя – це як біг на довгі дистанції: залежить від того, як ви рухаєтеся вперед.",
            "Час – це найцінніший ресурс, який у нас є.",
            "Найкращі вчинки – це ті, які ви робите власними руками.",
            "Не забувайте, що ви тільки один раз проживаєте цей день, тому живіть його на повну!",
            "Не витрачайте час на щось, що не зробить вас щасливими.",
            "Час – це гроші, але гроші не можуть купити час.",
            "Час – це те, що тобі дозволяється витрачати, коли ти не робиш нічого.",
            "Час – це те, що ви витрачаєте на мрії.",
            "Час – це те, що ви витрачаєте на мрії, а не на реальність.",
            "Час – це те, що ви витрачаєте на мрії, але не реалізовуєте.",
            "Нехай ваші мрії будуть більшими, ніж ваші страхи, а ваші дії – сильнішими, ніж ваші слова.",
            "Час – це все, що вам потрібно для того, щоб зробити свої мрії реальністю.",
            "Час – це вся ваша життєва сила, яку ви витрачаєте на те, щоб домогтися своїх мрій.",
            "Час – це те, що ви витрачаєте на свої мрії, але вам треба витрачати його на дії.",
            "Час – це те, що ви витрачаєте на мрії, але не робите.",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thoughtTextView = findViewById(R.id.thoughtTextView);
        nextButton = findViewById(R.id.nextButton);
        saveButton = findViewById(R.id.saveButton);
        savedThoughtsButton = findViewById(R.id.savedThoughtsButton);

        showRandomThought();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomThought();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveThought();
            }
        });

        savedThoughtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SavedThoughtsActivity.class);
                startActivity(intent);
            }
        });

        famousPeopleButton = findViewById(R.id.famousPeopleButton);

        famousPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FamousPeopleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showRandomThought() {
        Random random = new Random();
        int index = random.nextInt(thoughts.length);
        thoughtTextView.setText(thoughts[index]);
    }

    private void saveThought() {
        String thought = thoughtTextView.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("SavedThoughts", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> savedThoughts = sharedPreferences.getStringSet("savedThoughts", new HashSet<String>());
        savedThoughts.add(thought);
        editor.putStringSet("savedThoughts", savedThoughts);
        editor.apply();
        Toast.makeText(this, "Цитата збережена: " + thought, Toast.LENGTH_SHORT).show();
    }



}
