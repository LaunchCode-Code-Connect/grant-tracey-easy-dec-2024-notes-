package com.notes.controllers;

import com.notes.entities.Note;
import com.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("notes")
    public Note createNote(@RequestBody Note newNote){  return noteService.createNote(newNote); };

    @GetMapping("notes")
    public List<Note> fetchNoteList(){ return noteService.fetchNoteList(); };

    @PutMapping("notes/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable("id") Integer noteId)
    {
        return noteService.updateNote(
                note, noteId
        );
    }

    @DeleteMapping("note/{id}")
    public String deleteNote (@PathVariable("id") Integer noteId)
    {
       noteService.deleteNoteById(noteId);
       return "Note deleted successfully";
    }
}
