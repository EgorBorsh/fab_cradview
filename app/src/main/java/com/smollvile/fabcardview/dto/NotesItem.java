package com.smollvile.fabcardview.dto;


public class NotesItem {
    private String notes;
    private String date;
    private Long id;

    public NotesItem(String notes, String date, Long id) {
        this.notes = notes;
        this.date = date;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
