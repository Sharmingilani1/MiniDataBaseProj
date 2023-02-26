package com.notesandtaggs.minidatabaseproj;
import javax.persistence.*;

@Entity
@Table(name = "notetags")

public class NoteTags {
    @Id
    @Column(name = "noteTagsId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noteTagId;

    @Column(name = "noteId")
    private int noteId;

    @Column(name = "tagId")
    private int tagId;

    public void setId(int noteTagId) {
        this.noteTagId = noteTagId;
    }

    public int getnoteTagId() {
        return noteTagId;
    }

    public int noteTagId() {
        return noteTagId;
    }

//    @Id
//    @Column(name = "noteTagsId")
//    @GeneratedValue(strategy = GenerationType.AUTO)




}
