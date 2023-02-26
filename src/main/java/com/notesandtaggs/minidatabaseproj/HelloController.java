package com.notesandtaggs.minidatabaseproj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.persistence.*;
import java.util.List;

public class HelloController {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    private static ObservableList<String> OPTIONS = FXCollections.observableArrayList();

    public List<Notes> notes;
    public List<Notes> notes2;
    public List<Tags> tags;
    public List<Tags> tags2;
    public List<NoteTags> notetags;

    @FXML
    public ComboBox comboBox;
    @FXML

    public int notetagId;

    @FXML
    public TextField textFieldtitle;

    @FXML
    public TextField textFieldcontent;

    @FXML
    public TextField textFieldtagid;

    @FXML
    public TextField textFieldtagcontent;

    @FXML
    public TextField textFieldnotid;

    public HelloController() {
    }

    @FXML
     void initialize() {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Notes> allNotesQuery = entityManager.createQuery("from Notes", Notes.class);
        notes = allNotesQuery.getResultList();

        TypedQuery<Tags> allTagsQuery = entityManager.createQuery("from Tags", Tags.class);
        tags = allTagsQuery.getResultList();

        TypedQuery<NoteTags> allNoteTagsQuery = entityManager.createQuery("from NoteTags", NoteTags.class);
        notetags = allNoteTagsQuery.getResultList();

        for (Notes n : notes) {
            OPTIONS.add(n.getTitle());
        }

        comboBox.setItems(OPTIONS);
    }

    public void pushingnyAnteckningButton(ActionEvent e) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        transaction = entityManager.getTransaction();
        transaction.begin();

        Query query  = entityManager.createNativeQuery("INSERT INTO note (title, content) " +
                " VALUES(?,?)");

        query.setParameter(1, textFieldtitle.getCharacters().toString());
        query.setParameter(2, textFieldcontent.getCharacters().toString());

        query.executeUpdate();

        Query query2  = entityManager.createNativeQuery("INSERT INTO tag (content) " +
               " VALUES(?)");

        query2.setParameter(1, textFieldtagcontent.getCharacters().toString());
        query2.executeUpdate();


        TypedQuery<Notes> allNotesQuery1 = entityManager.createQuery("from Notes WHERE noteId=(SELECT max(noteId) FROM Notes)", Notes.class);
        notes2 = allNotesQuery1.getResultList();

        TypedQuery<Tags> allNotesQuery2 = entityManager.createQuery("from Tags WHERE tagId=(SELECT max(tagId) FROM Tags)", Tags.class);
        tags2 = allNotesQuery2.getResultList();


        Query query3  = entityManager.createNativeQuery("INSERT INTO Notetags (noteId, tagId) " +
                " VALUES(?,?)");

        query3.setParameter(1, notes2.get(0).getNoteId());
        query3.setParameter(2, tags2.get(0).getTagId());
        query3.executeUpdate();
        transaction.commit();

    }

    public void pushingreadAnteckningButton(ActionEvent e) {

        int n = comboBox.getSelectionModel().getSelectedIndex();

        textFieldcontent.setText((notes.get(n).getContent()));
        textFieldtitle.setText(notes.get(n).getTitle());

        textFieldtagcontent.setText(tags.get(n).getContent());

        textFieldnotid.setEditable(false);
        textFieldnotid.setText(Integer.toString(notes.get(n).getNoteId()));

        textFieldtagid.setEditable(false);
        textFieldtagid.setText(Integer.toString(tags.get(n).getTagId()));

        notetagId = (notetags.get(n).getnoteTagId());
    }


    public void pushingUpdateButton(ActionEvent e) {

        String updatetitle = textFieldtitle.getCharacters().toString();
        String updatecontent = textFieldcontent.getCharacters().toString();
        String updatetag = textFieldtagcontent.getCharacters().toString();

        int nId = Integer.parseInt(textFieldnotid.getCharacters().toString());
        int tId = Integer.parseInt(textFieldtagid.getCharacters().toString());

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        transaction = entityManager.getTransaction();
        transaction.begin();

        Tags tagToUpdate = entityManager.find(Tags.class,tId);
        tagToUpdate.settagdata(updatetag);

        Notes noteToUpdate = entityManager.find(Notes.class,nId);
        noteToUpdate.setnotedata( updatetitle,updatecontent);

        entityManager.merge(tagToUpdate);
        entityManager.merge(noteToUpdate);

        transaction.commit();
    }

    public void pushingDeleteTagg(ActionEvent e) {

        int nId = Integer.parseInt(textFieldnotid.getCharacters().toString());
        int tId = Integer.parseInt(textFieldtagid.getCharacters().toString());
        int ntId = notetagId;

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        transaction = entityManager.getTransaction();
        transaction.begin();

        NoteTags noteTagsToBeRemoved = entityManager.find(NoteTags.class, ntId);
        entityManager.remove(noteTagsToBeRemoved);
        entityManager.flush();

        Notes notesToBeRemoved = entityManager.find(Notes.class, nId);
        entityManager.remove(notesToBeRemoved);
        entityManager.flush();

        Tags tagsToBeRemoved = entityManager.find(Tags.class, tId);
        entityManager.remove(tagsToBeRemoved);
        entityManager.flush();
        transaction.commit();
    }

    }