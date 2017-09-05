package com.example.nazir.homework6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.nazir.homework6.cPolisTypes.polisAid;
import static com.example.nazir.homework6.cPolisTypes.polisFlat;
import static com.example.nazir.homework6.cPolisTypes.polisKasko;
import static com.example.nazir.homework6.cPolisTypes.polisOsago;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    PolisAdapter adapter;

    ImageView buttonSortUpImage;
    ImageView buttonSortDownImage;

    List<PolisEntity> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViews();
        configureRecyclerView();
        configureSortingButtons();
    }

    private void configureSortingButtons() {
        buttonSortUpImage = (ImageView) findViewById(R.id.imageSortUp);
        buttonSortDownImage =(ImageView) findViewById(R.id.imageSortDown);

        buttonSortUpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.SortItemsUp();
            }
        });

        buttonSortDownImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.SortItemsDown();
            }
        });
    }

    public  void  loadPolises() {
        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(new PolisEntity(polisOsago, "EEE0858555888", "Nissan Qashqai", new Date(2016-1900, 8-1, 20), new Date(2017-1900, 8-1, 19)));
        items.add(new PolisEntity(polisFlat, "EAE0858555887", "г.Москва, ул Дмитриевского, д. 5, кв. 55", new Date(2016-1900, 9-1, 12), new Date(2017-1900, 9-1, 11)));
        items.add(new PolisEntity(polisKasko, "EDE0858555886", "Nissan Qashqai", new Date(2016-1900, 3-1, 24), new Date(2017-1900, 3-1, 23)));
        items.add(new PolisEntity(polisAid, "ECE0858555885", "Снегирев Иван Петрович", new Date(2016-1900, 10-1, 28), new Date(2017-1900, 10-1, 27)));
        items.add(new PolisEntity(polisOsago, "EEE0858555888", "Nissan X-Trail", new Date(2016-1900, 10-1, 26), new Date(2017-1900, 10-1, 25)));
        items.add(new PolisEntity(polisFlat, "EAE0858555887", "г.Ульяновск, ул Денина, д. 1, кв. 1", new Date(2016-1900, 8-1, 20), new Date(2017-1900, 8-1, 19)));
        items.add(new PolisEntity(polisKasko, "EDE0858555886", "Toyota Camry", new Date(2016-1900, 12-1, 24), new Date(2017-1900, 12-1, 23)));
        items.add(new PolisEntity(polisAid, "ECE0858555885", "Иванов Иван Иванович", new Date(2016-1900, 8-1, 28), new Date(2017-1900, 8-1, 27)));
        items.add(new PolisEntity(polisOsago, "EEE0858555888", "Kia Sorento", new Date(2016-1900, 11-1, 12), new Date(2017-1900, 11-1, 11)));
        items.add(new PolisEntity(polisFlat, "EAE0858555887", "г.Самара, ул Тюполева, д. 89, кв. 235", new Date(2016-1900, 9-1, 11), new Date(2017-1900, 9-1, 10)));
    }

    private void configureViews() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureRecyclerView() {
        adapter = new PolisAdapter();
        recycler.setAdapter(adapter);

        loadPolises();
        adapter.setNewItems(items);

        //Observable.just("Load polises")
     /*   Observable.just(items)
                .subscribeOn(Schedulers.io())
                .map(cmd -> loadPolises())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PolisEntity>() {
                    @Override
                    public void onCompleted() {
                        //showToast( "Completed");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //showToast( "Error");
                    }

                    @Override
                    public void onNext(PolisEntity s) {
                        //showToast(s);
                    }

                });
*/


        ;

        /*List<PolisEntity> items = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Observable.from(items)
                .window(4)
                .flatMap(t -> t)
                .subscribe(t -> showToast(t.toString()));

        Observable.just("Test")
                .subscribe( new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //showToast( "Completed");
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        //showToast( "Error");
                    }
                    @Override
                    public void onNext(String s) {
                        //showToast(s);
                    }
                });*/

    /*
        adapter.addItem(new PolisEntity(polisOsago, "EEE0858555888", "Nissan Qashqai", new Date(2016-1900, 8-1, 20), new Date(2017-1900, 8-1, 19)));
        adapter.addItem(new PolisEntity(polisFlat, "EAE0858555887", "г.Москва, ул Дмитриевского, д. 5, кв. 55", new Date(2016-1900, 9-1, 12), new Date(2017-1900, 9-1, 11)));
        adapter.addItem(new PolisEntity(polisKasko, "EDE0858555886", "Nissan Qashqai", new Date(2016-1900, 3-1, 24), new Date(2017-1900, 3-1, 23)));
        adapter.addItem(new PolisEntity(polisAid, "ECE0858555885", "Снегирев Иван Петрович", new Date(2016-1900, 10-1, 28), new Date(2017-1900, 10-1, 27)));
        adapter.addItem(new PolisEntity(polisOsago, "EEE0858555888", "Nissan X-Trail", new Date(2016-1900, 10-1, 26), new Date(2017-1900, 10-1, 25)));
        adapter.addItem(new PolisEntity(polisFlat, "EAE0858555887", "г.Ульяновск, ул Денина, д. 1, кв. 1", new Date(2016-1900, 8-1, 20), new Date(2017-1900, 8-1, 19)));
        adapter.addItem(new PolisEntity(polisKasko, "EDE0858555886", "Toyota Camry", new Date(2016-1900, 12-1, 24), new Date(2017-1900, 12-1, 23)));
        adapter.addItem(new PolisEntity(polisAid, "ECE0858555885", "Иванов Иван Иванович", new Date(2016-1900, 8-1, 28), new Date(2017-1900, 8-1, 27)));
        adapter.addItem(new PolisEntity(polisOsago, "EEE0858555888", "Kia Sorento", new Date(2016-1900, 11-1, 12), new Date(2017-1900, 11-1, 11)));
        adapter.addItem(new PolisEntity(polisFlat, "EAE0858555887", "г.Самара, ул Тюполева, д. 89, кв. 235", new Date(2016-1900, 9-1, 11), new Date(2017-1900, 9-1, 10)));
        */
    }
}
