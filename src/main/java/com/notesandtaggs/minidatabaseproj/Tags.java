package com.notesandtaggs.minidatabaseproj;

import javax.persistence.*;

@Entity
@Table (name = "Tag")

public class Tags {
    @Id
    @Column(name = "tagId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tagId;

    @Column(name = "content")
    private String content;


    public Tags(){

    }
    public Tags(int tagId, String content) {
        this.tagId = tagId;
        this.content = content;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void settagdata(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    }


