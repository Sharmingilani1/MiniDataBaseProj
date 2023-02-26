package com.notesandtaggs.minidatabaseproj;

import javax.persistence.*;

@Entity
@Table(name = "note")

public class Notes {

    @Id
    @Column(name = "noteId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noteId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Notes() {

    }


    public int getNoteId() {
        return noteId;
    }

    public Notes(String title, String content) {;
        this.title = title;
        this.content = content;
    }


    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void setnotedata(String title, String content)  {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
