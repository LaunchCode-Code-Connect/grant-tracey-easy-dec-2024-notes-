package com.notes.controllers;

import com.notes.entities.Note;
import com.notes.entities.User;
import com.notes.repositories.NoteRepository;
import com.notes.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

//    @Autowired
//    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    // Methods for testing
//    @GetMapping("")
//    public List<Note> fetchNoteList(){ return noteService.fetchNoteList(); };
//
//    @GetMapping("/id/{id}")
//    public Optional<Note> fetchNote(@PathVariable("id")
//                                    Integer noteId)
//    {
//        return noteService.findNoteById(noteId);
//    };
//
//    @PostMapping("/create")
//    public Note createNote(@RequestBody Note newNote){  return noteService.createNote(newNote); };
//
//    @PutMapping("/{id}")
//    public Note updateNote(@RequestBody Note note, @PathVariable("id") Integer noteId)
//    {
//        return noteService.updateNote(
//                note, noteId
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteNote (@PathVariable("id") Integer noteId)
//    {
//       noteService.deleteNoteById(noteId);
//       return "Note deleted successfully";
//    }

    // Methods for application use
    @GetMapping("/")
    public String displayNotes(Model model) {
        model.addAttribute("title", "Notes");
        model.addAttribute("h1", "Your Notes");
        return "notes/index";
    }

    @GetMapping("/add")
    public String displayNoteForm(Model model) {
        model.addAttribute(new Note());
        return "notes/add";
    }

    @PostMapping("/add")
    public String processNoteForm(@ModelAttribute @Valid Note newNote,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Note");
            return "notes/add";
        }
        noteRepository.save(newNote);
        return "redirect:";
    }

    @GetMapping("/{noteId}")
    public String displayViewNote(Model model, @PathVariable int noteId) {

        Optional<Note> optNote = noteRepository.findById(noteId);
        if (optNote.isPresent()) {
            Note note = (Note) optNote.get();
            model.addAttribute("note", note);
            return "notes/view";
        } else {
            return "redirect:";
        }

    }

//    @PostMapping("/{noteId}")
//    public String updateNote(Model model, @PathVariable Note note, Integer noteId){
//
//    }

    @PostMapping("/{noteId}")
    public String deleteNote (@PathVariable Integer noteId)
    {
        noteRepository.deleteById(noteId);
        return "redirect:";
    }


}