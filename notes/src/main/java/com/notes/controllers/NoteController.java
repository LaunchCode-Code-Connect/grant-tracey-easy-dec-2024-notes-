package com.notes.controllers;

import com.notes.entities.Note;
import com.notes.repositories.NoteRepository;
import com.notes.repositories.UserRepository;
import com.notes.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;


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
            model.addAttribute("h3", "All Notes");
            model.addAttribute("notes", noteRepository.findAll());
        } else {
            Optional<Note> result = noteRepository.findById(noteId);
            if (result.isEmpty()) {
                model.addAttribute("h3", "Invalid Note ID: " + noteId);
            } else {
                Note note = result.get();
                model.addAttribute("h3", "Title: " + note.getTitle());
                model.addAttribute("notes", note.getContent());
            }
        }

        return "notes/list";
    }

    @GetMapping("/add")
    public String displayNoteForm(Model model) {

//        Optional<User> user = userRepository.findById(userId);
//        System.out.println(user.get().getUsername()
//
//        );
        model.addAttribute(new Note());

        return "notes/add";
    }

    @PostMapping("/add")
    public String processNoteForm(@ModelAttribute @Valid Note newNote,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("h3", "Add Note");
            return "notes/add";
        }

        noteRepository.save(newNote);
        return "redirect:list";
    }

    @GetMapping("/detail")
    public String displayViewNote(@RequestParam int noteId, Model model) {

        Optional<Note> optNote = noteRepository.findById(noteId);

        if (optNote.isEmpty())
        {
            model.addAttribute("h3", "Invalid Id" + noteId);
        } else {
            Note note = optNote.get();
            model.addAttribute("h3", note.getTitle());
            model.addAttribute("note", note);
        }
        return "notes/detail";

    }

    @PostMapping("/detail/{noteId}")
    public String deleteNote (@PathVariable("noteId") Integer noteId)
    {
        noteRepository.deleteById(noteId);
        return "redirect:../list";
    }

    @GetMapping("/update")
    public String displayEditNote(@RequestParam int noteId, Model model) {

        Optional<Note> optNote = noteRepository.findById(noteId);

        if (optNote.isEmpty())
        {
            model.addAttribute("h3", "Invalid Id" + noteId);
        } else {
            Note note = optNote.get();
            model.addAttribute("h3", note.getTitle());
            model.addAttribute("note", note);
        }
        return "notes/update";

    }

    @PostMapping("update/{noteId}")
    public String updateNote(@PathVariable("noteId") int noteId, @Valid Note note,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            note.setNoteId(noteId);
            return "notes/add";
        }

        noteRepository.save(note);
        return "redirect:/dashboard";
    }

}