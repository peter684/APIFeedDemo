package com.example.apifeeddemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ItemAdapter adapter = new ItemAdapter(this);

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                adapter.submitList(items);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
