package com.notes.entities;

import jakarta.persistence.*;

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

    private String title;

    private String content;

    private Date createdAt;

    private Date updatedAt;

    public Note(){

    }

    public Note(User user, String title, String content, Date createdAt, Date updatedAt) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getNoteId() {
        return noteId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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
