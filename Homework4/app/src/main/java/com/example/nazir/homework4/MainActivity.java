package com.example.nazir.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    PolisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViews();
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        adapter = new PolisAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter.addItem(new PolisEntity(0, "EEE0858555888", "Nissan Qashqai", new Date(2016-1900, 8-1, 20), new Date(2017-1900, 8-1, 19)));
        adapter.addItem(new PolisEntity(2, "EAE0858555887", "г.Москва, ул Дмитриевского, д. 5, кв. 55", new Date(2016-1900, 9-1, 12), new Date(2017-1900, 9-1, 11)));
        adapter.addItem(new PolisEntity(1, "EDE0858555886", "Nissan Qashqai", new Date(2016-1900, 3-1, 24), new Date(2017-1900, 3-1, 23)));
        adapter.addItem(new PolisEntity(3, "ECE0858555885", "Снегирев Иван Петрович", new Date(2016-1900, 10-1, 28), new Date(2017-1900, 10-1, 27)));
        adapter.addItem(new PolisEntity(0, "EEE0858555888", "Nissan X-Trail", new Date(2016-1900, 10-1, 26), new Date(2017-1900, 10-1, 25)));
        adapter.addItem(new PolisEntity(2, "EAE0858555887", "г.Ульяновск, ул Денина, д. 1, кв. 1", new Date(2016-1900, 8-1, 20), new Date(2017-1900, 8-1, 19)));
        adapter.addItem(new PolisEntity(1, "EDE0858555886", "Toyota Camry", new Date(2016-1900, 12-1, 24), new Date(2017-1900, 12-1, 23)));
        adapter.addItem(new PolisEntity(3, "ECE0858555885", "Иванов Иван Тванович", new Date(2016-1900, 8-1, 28), new Date(2017-1900, 8-1, 27)));
        adapter.addItem(new PolisEntity(0, "EEE0858555888", "Kia Sorento", new Date(2016-1900, 11-1, 12), new Date(2017-1900, 11-1, 11)));
        adapter.addItem(new PolisEntity(2, "EAE0858555887", "г.Самара, ул Тюполева, д. 89, кв. 235", new Date(2016-1900, 9-1, 11), new Date(2017-1900, 9-1, 10)));
    }

    private void configureViews() {
        recycler = (RecyclerView) findViewById(R.id.recycler);

    }

}
