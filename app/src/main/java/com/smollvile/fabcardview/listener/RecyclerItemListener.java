package com.smollvile.fabcardview.listener;


import android.view.View;

import com.smollvile.fabcardview.dto.NotesItem;

public interface RecyclerItemListener {
    void onClickItem(View v, NotesItem notesItem);
}
