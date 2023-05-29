package com.example.a10120160rendiuts;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a10120160rendiuts.helper.Helper;
import com.example.a10120160rendiuts.model.Note;

public class InputActivity extends AppCompatActivity {

    private EditText et_title, et_category,et_content, et_created_at,et_updated_at;
    private TextView tv_title_page;
    private Helper db = new Helper(this);
    private Button btn_save, btn_back;
    private Intent intent;
    private String id, title, category, content, created_at, updated_at;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        et_title = findViewById(R.id.et_title);
        et_category = findViewById(R.id.et_category);
        et_content = findViewById(R.id.et_content);

        tv_title_page = findViewById(R.id.tv_title_page);

        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        category = getIntent().getStringExtra("category");
        content  = getIntent().getStringExtra("content");


        try {
            if(title.length() > 0) {
                tv_title_page.setText("Ubah Catatan");
                et_title.setText(title);
                et_category.setText(category);
                et_content.setText(content);
            } else {
                tv_title_page.setText("Tambah Catatan");
            }
        } catch (Exception e) {
            tv_title_page.setText("Tambah Catatan");
        }





        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("")){
                        save();
                    }else{
                        edit();
                    }
                }catch (Exception e){
                    Toast.makeText(InputActivity.this,  e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public void save(){
        if (String.valueOf(et_title.getText()).equals("") || String.valueOf(et_category.getText()).equals("")|| String.valueOf(et_content.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        }else{
            Note note = new Note(
                    et_title.getText().toString(),
                    et_category.getText().toString(),
                    et_content.getText().toString()
            );
            db.Insert(note);
            Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Catatan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void edit(){
        if (String.valueOf(et_title.getText()).equals("") ||String.valueOf(et_category.getText()).equals("")||String.valueOf(et_content.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        }else{
            Note note = new Note(
                    et_title.getText().toString(),
                    et_category.getText().toString(),
                    et_content.getText().toString()
            );
            db.Update(Integer.parseInt(id), note);
            Toast.makeText(getApplicationContext(), "Berhasil Mengubah Catatan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}