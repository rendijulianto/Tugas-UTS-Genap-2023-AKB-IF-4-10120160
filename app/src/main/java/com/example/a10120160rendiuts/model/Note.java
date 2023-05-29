package com.example.a10120160rendiuts.model;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
public class Note {
    private String title, category, content,created_at, updated_at;
    private Integer id;
    public Note(Integer id, String title, String category, String content, String created_at, String updated_at) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public Note(String title, String category, String content, String created_at, String updated_at) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Note(String title, String category, String content) {
        this.title = title;
        this.category = category;
        this.content = content;
    }
    public Note(Integer id, String title, String category, String content) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;

    }
    public Note() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
