package com.example.ezbro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Armas extends AppCompatActivity {

    List<ListElement> elements;
    private String palabra ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas);

        init();
    }
    public void init()
    {
        elements = new ArrayList<>();
        elements.add(new ListElement("#850617", "Martina","Santiago", "Diva"));
        elements.add(new ListElement("#850617", "Simon","Santiago", "Locura y Maldad"));
        elements.add(new ListElement("#850617", "Enyerver","MaipuCity", "7 Gramos"));
        elements.add(new ListElement("#850617", "Santiago","Santiago", "Truzkita"));
        elements.add(new ListElement("#850617", "Javier","MaipuCity", "JP"));



        ListAdapter listAdapter = new ListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.ListRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(listAdapter);
    }
}