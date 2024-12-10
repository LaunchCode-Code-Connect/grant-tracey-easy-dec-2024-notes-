package com.notes.controllers;

import com.notes.entities.Note;
import com.notes.entities.User;
import com.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("")
    public Note createNote(@RequestBody Note newNote){  return noteService.createNote(newNote); };

    @GetMapping("")
    public List<Note> fetchNoteList(){ return noteService.fetchNoteList(); };

    @GetMapping("/{id}")
    public Optional<Note> fetchNote(@PathVariable("id")
                                        Integer noteId)
    {
        return noteService.findNoteById(noteId);
    };

    @PutMapping("/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable("id") Integer noteId)
    {
        return noteService.updateNote(
                note, noteId
        );
    }

    @DeleteMapping("/{id}")
    public String deleteNote (@PathVariable("id") Integer noteId)
    {
       noteService.deleteNoteById(noteId);
       return "Note deleted successfully";
    }
}
