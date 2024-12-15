package com.notes.controllers;

import com.notes.entities.Note;
import com.notes.entities.User;
import com.notes.repositories.NoteRepository;
import com.notes.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

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
    @GetMapping("/list")
    public String displayNotes(@RequestParam(required = false) Integer noteId, Model model) {
    // Displays all notes unless noteId parameter is provided
        if (noteId == null) {
            model.addAttribute("h1", "All Notes");
            model.addAttribute("notes", noteRepository.findAll());
        } else {
            Optional<Note> result = noteRepository.findById(noteId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Note ID: " + noteId);
            } else {
                Note note = result.get();
                model.addAttribute("title", "Title: " + note.getTitle());
                model.addAttribute("notes", note.getContent());
            }
        }

        return "notes/list";
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

    @GetMapping("/detail")
    public String displayViewNote(@RequestParam int noteId, Model model) {

        Optional<Note> optNote = noteRepository.findById(noteId);

        if (optNote.isEmpty())
        {
            model.addAttribute("h1", "Invalid Id" + noteId);
        } else {
            Note note = optNote.get();
            model.addAttribute("h1", note.getTitle());
            model.addAttribute("note", note);
        }
        return "notes/detail";

    }

//    @PostMapping("/{noteId}")
//    public String updateNote(Model model, @PathVariable Note note, Integer noteId){
//
//    }

    @PostMapping("/detail/{noteId}")
    public String deleteNote (@PathVariable("noteId") Integer noteId)
    {
        noteRepository.deleteById(noteId);
        //REVISIT
        return "notes/list";
    }

//    @PostMapping("/update")
//    public Note updateNote(@RequestBody Note note, @PathVariable("noteId") Integer noteId)
//    {
//        return noteService.updateNote(
//                note, noteId
//        );
//    }


    @GetMapping("/{noteId}/update")
    public String displayEditNote(Model model, @PathVariable("noteId") Integer noteId) {

        Optional<Note> optNote = noteRepository.findById(noteId);
        if (optNote.isPresent()) {
            Note note = (Note) optNote.get();
            model.addAttribute("h1", note.getTitle());
            model.addAttribute("h3", note.getContent());
            model.addAttribute("p", "Note #" + note.getNoteId());
            return "notes/update";
        } else {
            return "redirect:";
        }

    }
    @PostMapping("/{noteId}/update")
    public ResponseEntity<Note> updateNote(@PathVariable("noteId") Integer noteId,
                                           @ModelAttribute Note updatedNote)
    {

        Note note = noteService.updateNote(updatedNote, noteId);

        return ResponseEntity.ok(note);
    }


}