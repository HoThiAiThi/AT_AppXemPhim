package com.example.bt_cuoiky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Phim extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    FloatingActionButton floatingActionButton;
    PhimAdapter phimAdapter;
    LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phim);

        recyclerView = (RecyclerView) findViewById(R.id.rcv_phim);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        searchView = (SearchView) findViewById(R.id.search_view);
        back = (LinearLayout) findViewById(R.id.back);
        searchView.clearFocus();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<PhimModel> options =
                new FirebaseRecyclerOptions.Builder<PhimModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("phim"), PhimModel.class)
                        .build();
        phimAdapter = new PhimAdapter(options, this);
        recyclerView.setAdapter(phimAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Phim.this, GiaoDienChinh.class));
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

    }

    protected void onStart() {
        super.onStart();
        phimAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        phimAdapter.stopListening();
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<PhimModel> options =
                new FirebaseRecyclerOptions.Builder<PhimModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("phim").orderByChild("tenPhim").startAt(str).endAt(str+"~"), PhimModel.class)
                        .build();
        phimAdapter = new PhimAdapter(options, this);
        phimAdapter.startListening();
        recyclerView.setAdapter(phimAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), GiaoDienChinh.class));
    }
}