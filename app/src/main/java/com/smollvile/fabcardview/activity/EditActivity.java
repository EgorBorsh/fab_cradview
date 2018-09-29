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

public class EditActivity extends AppCompatActivity{

    private static final String KEY_NOTES = "NOTES_KEY";
    private static final String KEY_POS = "POS_KEY";

    EditText editText;
    Button saveEdit, deleteEdit;
    String editNotes;
    Long id;

    NotesDao notesDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        notesDao = daoSession.getNotesDao();

        id = getIntent().getLongExtra(KEY_POS, 1);
        editNotes = getIntent().getStringExtra(KEY_NOTES);

        initEdit();
        initSave();
    }

    private void initSave() {
        saveEdit = (Button) findViewById(R.id.save_edit);
        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    Notes notes = notesDao.queryBuilder().where(NotesDao.Properties.Id.eq(id)).list().get(0);
                    notes.setTxtNotes(editText.getText().toString());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
                    String strDate = simpleDateFormat.format(new Date());
                    notes.setTxtDate(strDate);
                    notesDao.update(notes);
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(EditActivity.this, R.string.toast_button_click, Toast.LENGTH_LONG).show();
                }
            }
        });

        deleteEdit = (Button) findViewById(R.id.delete_edit);
        deleteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notes notes = notesDao.queryBuilder().where(NotesDao.Properties.Id.eq(id)).list().get(0);
                notesDao.delete(notes);
                List<Notes> list = notesDao.queryBuilder().list();
                notesDao.deleteAll();
                for (int i = 0 ; i < list.size(); i++)
                {
                    Notes notes1 = list.get(i);
                    notes1.setId((long) (i+1));
                    notesDao.insert(notes1);
                }
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initEdit() {
        editText = (EditText) findViewById(R.id.edit_notes_edit);
        editText.setText(editNotes);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
