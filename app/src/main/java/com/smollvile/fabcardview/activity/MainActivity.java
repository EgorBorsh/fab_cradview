package com.smollvile.fabcardview.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
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

    private static final String KEY_NOTES = "NOTES_KEY";
    private static final String KEY_POS = "POS_KEY";

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
            String txtDate = listNotes.get(i).getTxtDate();
            Long id = listNotes.get(i).getId();

            NotesItem notesItem = new NotesItem(txtNotes, txtDate, id);
            list.add(notesItem);
        }
        adapter = new RecyclerViewAdapter(list, this, new RecyclerItemListener() {
            @Override
            public void onClickItem(View v, NotesItem notesItem) {

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(KEY_POS, notesItem.getId());
                intent.putExtra(KEY_NOTES, notesItem.getNotes());
                startActivity(intent);
            }
        });

        recycler.setAdapter(adapter);
    }

    @SuppressLint("ResourceAsColor")
    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
