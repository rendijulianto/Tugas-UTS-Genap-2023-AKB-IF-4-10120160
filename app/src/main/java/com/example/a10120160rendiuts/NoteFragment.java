package com.example.a10120160rendiuts;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a10120160rendiuts.adapter.NoteAdapter;
import com.example.a10120160rendiuts.helper.Helper;
import com.example.a10120160rendiuts.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    ListView listView;
    AlertDialog.Builder dialog;
    List<Note> notes = new ArrayList<>();
    NoteAdapter adapter;
    Helper db = new Helper(getContext());
    Button btn_add, btn_delete, btn_update;
    FloatingActionButton fab;

    ImageView ivHapus, ivEdit;
    Button btnxxx;

    public NoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteFragment.
     */
    // TODO: Rename and change types and number of parameters

    private String mParam1;
    private String mParam2;

    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_note, container, false);
        db = new Helper(getContext());
        fab = view.findViewById(R.id.fab);

        listView = view.findViewById(R.id.lv_item);
        adapter = new NoteAdapter(getActivity(), notes);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InputActivity.class));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = String.valueOf(notes.get(i).getId());
                final String title = notes.get(i).getTitle();
                final String category = notes.get(i).getCategory();
                final String content = notes.get(i).getContent();
                final String created_at = notes.get(i).getCreated_at();
                final String updated_at = notes.get(i).getUpdated_at();

                final CharSequence[] dialogItem = {"Edit", "Hapus"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getContext(), InputActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("title", title);
                                intent.putExtra("category", category);
                                intent.putExtra("content", content);
                                intent.putExtra("created_at", created_at);
                                intent.putExtra("updated_at", updated_at);

                                startActivity(intent);
                                break;
                            case 1:
                                db.Delete(Integer.parseInt(id));
                                Toast.makeText(getContext(), "Berhasil menghapus catatan", Toast.LENGTH_SHORT).show();
                                notes.clear();
                                read();
                                break;
                        }
                    }
                }).show();

                return false;
            }
        });
        read();

        return view;
    }

    private void read() {
        ArrayList<HashMap<String, String>> rows = db.GetAll();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String title = rows.get(i).get("title");
            String category = rows.get(i).get("category");
            String content = rows.get(i).get("content");
            String created_at = rows.get(i).get("created_at");
            String updated_at = rows.get(i).get("updated_at");
            Note note = new Note(Integer.parseInt(id), title, category, content, created_at, updated_at);
            notes.add(note);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        notes.clear();
        read();
    }
}