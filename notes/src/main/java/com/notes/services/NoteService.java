package com.notes.services;

import com.notes.entities.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    //Create
    Note createNote(Note note);
    //Read
    List<Note> fetchNoteList();
    Optional<Note> findNoteById(int noteId);
    //Update
    Note updateNote(Integer noteId, Note note);
    //Delete
    void deleteNoteById(Integer noteId);
}
