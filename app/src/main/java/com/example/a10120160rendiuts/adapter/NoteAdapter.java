package com.example.a10120160rendiuts.adapter;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.a10120160rendiuts.R;
import com.example.a10120160rendiuts.model.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Note> notes;

    public NoteAdapter(Activity activity, List<Note> notes) {
        this.activity = activity;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null){
            view = inflater.inflate(R.layout.notes, null);
        }

        if (view != null){
            TextView tv_title = view.findViewById(R.id.tv_title);
            TextView tv_category = view.findViewById(R.id.tv_category);
            TextView tv_content = view.findViewById(R.id.tv_content);
            TextView tv_created_at = view.findViewById(R.id.tv_created_at);
            TextView tv_updated_at = view.findViewById(R.id.tv_updated_at);


            Note Note = notes.get(i);
            tv_title.setText(Note.getTitle());
            tv_category.setText(Note.getCategory());
            tv_content.setText(Note.getContent());
            tv_created_at.setText(Note.getCreated_at());
            tv_updated_at.setText(Note.getUpdated_at());
        }

        return view;
    }
}