package com.smollvile.fabcardview.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smollvile.fabcardview.App;
import com.smollvile.fabcardview.R;
import com.smollvile.fabcardview.dao.DaoSession;
import com.smollvile.fabcardview.dao.Notes;
import com.smollvile.fabcardview.dao.NotesDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    EditText editNotes;
    Button save;

    NotesDao notesDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        notesDao = daoSession.getNotesDao();

        initEditText();
        initButton();
    }

    private void initButton() {
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editNotes.getText().toString().equals("")) {
                    Notes notes = new Notes();
                    notes.setTxtNotes(editNotes.getText().toString());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
                    String strDate = simpleDateFormat.format(new Date());
                    notes.setTxtDate(strDate);
                    List<Notes> list = notesDao.queryBuilder().list();
                    notes.setId((long) (list.size()+1));
                    notesDao.insert(notes);
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(AddActivity.this, R.string.toast_button_click, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initEditText() {
        editNotes = (EditText) findViewById(R.id.edit_notes);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
