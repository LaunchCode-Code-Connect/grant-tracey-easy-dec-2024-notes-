package com.notes.services;

import com.notes.entities.Note;
import com.notes.entities.User;
import com.notes.repositories.NoteRepository;
import com.notes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNote(Note newNote){ return noteRepository.save(newNote);}

    @Override
    public List<Note> fetchNoteList() {
        return (List<Note>) noteRepository.findAll();
    }

    @Override
    public Optional<Note> findNoteById(int noteId) {
        return noteRepository.findById(noteId);
    }

    @Override
    public Note updateNote(Note note, Integer noteId)
    {
        Note noteDB
                = noteRepository.findById(noteId).get();

        if (Objects.nonNull(note.getTitle())
                && !"".equalsIgnoreCase(
                note.getTitle())) {
            noteDB.setTitle(
                    note.getTitle());
        }
        if (Objects.nonNull(note.getContent())
            && !"".equalsIgnoreCase(
                    note.getContent())) {
            noteDB.setContent(
                note.getContent());
        }

        return noteRepository.save(noteDB);
    }

    @Override
    public void deleteNoteById(Integer noteId) {
        noteRepository.deleteById(noteId);
    }
}
