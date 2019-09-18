package com.example.mynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVh> {
    private Context context;
    List<Note> list;

    public NoteAdapter(MainActivity context, List<Note> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cell_layout,parent,false);
        return (new NoteVh((view)));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVh holder, int position) {
        final Note note=list.get(position);
        holder.mTvtitle.setText(note.getTitle());
        holder.mTvtext.setText(note.getText());

        holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(note);
            }
        });

        holder.mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUpdate(note);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoteVh extends RecyclerView.ViewHolder {

        TextView mTvtitle,mTvtext;
        Button mBtnUpdate,mBtnDelete;

        public NoteVh(@NonNull View itemView) {
            super(itemView);
            mTvtitle=itemView.findViewById(R.id.tv_title);
            mTvtext=itemView.findViewById(R.id.tv_text);

            mBtnDelete = itemView.findViewById(R.id.btn_delete);
            mBtnUpdate = itemView.findViewById(R.id.btn_update);
        }
    }

    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onUpdate(Note note);
        void onDelete(Note note);
    }

}
