package com.notes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private int noteId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public Note(){

    }

    public Note(User user, String title, String content, LocalDate createdAt, LocalDate updatedAt) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteId == note.noteId && Objects.equals(user, note.user) && Objects.equals(title, note.title) && Objects.equals(content, note.content) && Objects.equals(createdAt, note.createdAt) && Objects.equals(updatedAt, note.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, user, title, content, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
