package com.smollvile.fabcardview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smollvile.fabcardview.App;
import com.smollvile.fabcardview.R;
import com.smollvile.fabcardview.adapter.RecyclerViewAdapter;
import com.smollvile.fabcardview.dao.DaoSession;
import com.smollvile.fabcardview.dao.Notes;
import com.smollvile.fabcardview.dao.NotesDao;
import com.smollvile.fabcardview.dto.NotesItem;
import com.smollvile.fabcardview.listener.RecyclerItemListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recycler;

    NotesDao notesDao;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        notesDao = daoSession.getNotesDao();

        initFAB();
        initRecycler();
    }

    private void initRecycler() {
        recycler = (RecyclerView) findViewById(R.id.recycle);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        List<NotesItem> list = new ArrayList<>();

        List<Notes> listNotes = notesDao.queryBuilder().list();
        for (int i = 0; i < listNotes.size(); i++) {
            String txtNotes = listNotes.get(i).getTxtNotes();
            String txtDate = listNotes.get(i).getTxtNotes();

            NotesItem notesItem = new NotesItem(txtNotes, txtDate);
            list.add(notesItem);
        }
        adapter = new RecyclerViewAdapter(list, this, new RecyclerItemListener() {
            @Override
            public void onClickItem(View v, NotesItem notesItem) {

            }
        });

        recycler.setAdapter(adapter);
    }

    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
}
