package com.notes.services;

import com.notes.entities.Note;
import com.notes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(Note newNote){ return noteRepository.save(newNote);}


    public List<Note> fetchNoteList() {
        return (List<Note>) noteRepository.findAll();
    }


    public Optional<Note> findNoteById(int noteId) {
        return noteRepository.findById(noteId);
    }

//    @Override
//    public Note updateNote(Integer noteId, Note updatedNote) {
//        return noteRepository.findById(noteId).map(note -> {
//            note.setTitle(updatedNote.getTitle());
//            note.setContent(updatedNote.getContent());
//            return noteRepository.save(note);
//        }).orElseThrow(() -> new EntityNotFoundException("Note ID " + noteId + " not found"));
//    }
public Note updateNote(Integer noteId, Note updatedNote) {
    // Fetch the user from the database
    Optional<Note> existingNoteOpt = noteRepository.findById(noteId);

    if (existingNoteOpt.isPresent()) {
        Note existingNote = existingNoteOpt.get();
        // Update the fields
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        // Save the updated entity back to the database
        return noteRepository.save(existingNote);
    } else {
        throw new RuntimeException("Note with id " + noteId + " not found");
    }
}


//    @Override
//    public Note updateNote(Optional<Note> note, Integer noteId)
//    {
//        Note noteDB
//                = noteRepository.findById(noteId).get();
//
//        if (Objects.nonNull(note.getTitle())
//                && !"".equalsIgnoreCase(
//                note.getTitle())) {
//            noteDB.setTitle(
//                    note.getTitle());
//        }
//        if (Objects.nonNull(note.getContent())
//            && !"".equalsIgnoreCase(
//                    note.getContent())) {
//            noteDB.setContent(
//                note.getContent());
//        }
//
//        return noteRepository.save(noteDB);
//    }


    public void deleteNoteById(Integer noteId) {
        noteRepository.deleteById(noteId);
    }
}
