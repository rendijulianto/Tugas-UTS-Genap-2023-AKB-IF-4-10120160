package com.example.a10120160rendiuts.helper;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a10120160rendiuts.model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "10120160_Rendi_Notes";


    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE tbl_notes (id INTEGER PRIMARY KEY autoincrement, " +
                "title TEXT NOT NULL, " +
                "category TEXT NOT NULL, " +
                "content TEXT NOT NULL, " +
                "created_at datetime default current_timestamp," +
                "updated_at datetime default current_timestamp)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_notes");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> GetAll(){

        ArrayList<HashMap<String, String>> notes = new ArrayList<>();
        String QUERY = "SELECT * FROM tbl_notes Order by created_at desc";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("category", cursor.getString(2));
                map.put("content", cursor.getString(3));
                map.put("created_at", cursor.getString(4));
                map.put("updated_at", cursor.getString(5));

                notes.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public void Insert(Note note){
        SQLiteDatabase database = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        String QUERY = "INSERT INTO tbl_notes(title, category, content, created_at, updated_at) VALUES('"+note.getTitle()+"', '"+note.getCategory()+"', '"+note.getContent()+"', '"+date+"', null)";
        database.execSQL(QUERY);
    }

    public void Update(int id, Note note){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE tbl_notes SET title = '"+note.getTitle()+"', category = '"+note.getCategory()+"', content = '"+note.getContent()+"', updated_at = '"+date+"' WHERE id =" + id;
        database.execSQL(QUERY);
    }

    public void Delete(int Id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM tbl_notes WHERE id = " +Id;
        database.execSQL(QUERY);
    }

}
