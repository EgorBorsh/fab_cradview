package com.smollvile.fabcardview.listener;


import android.view.View;

import com.smollvile.fabcardview.dto.Notes;

public interface RecyclerItemListener {
    void onClickItem(View v, Notes notes);
}
