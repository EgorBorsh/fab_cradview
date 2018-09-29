package com.smollvile.fabcardview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smollvile.fabcardview.R;
import com.smollvile.fabcardview.dto.Notes;
import com.smollvile.fabcardview.listener.RecyclerItemListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    List<Notes> list = new ArrayList<>();
    Context context;
    RecyclerItemListener listener;

    public RecyclerViewAdapter(List<Notes> list, Context context, RecyclerItemListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false );
        final RecyclerViewHolder holder = new RecyclerViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickItem(v, getNotes(holder.getPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.txtNotes.setText(list.get(position).getNotes());
        holder.txtDate.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txtNotes, txtDate;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            txtNotes = (TextView) itemView.findViewById(R.id.text_notes);
            txtDate = (TextView) itemView.findViewById(R.id.text_date);
        }
    }

    private Notes getNotes(int position) {
        return list.get(position);
    }
}
