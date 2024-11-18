package global.goit.todolistapp.service;

import global.goit.todolistapp.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NoteService {

    private final Map<Long, Note> notes = new ConcurrentHashMap<>();

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        long id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with ID " + id + " not found.");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NoSuchElementException("Note with ID " + note.getId() + " not found.");
        }
        Note currentNote = notes.get(note.getId());
        currentNote.setTitle(note.getTitle());
        currentNote.setContent(note.getContent());
    }

    public Note getById(long id) {
        Note currentNote = notes.get(id);
        if (currentNote == null) {
            throw new NoSuchElementException("Note with ID " + id + " not found.");
        }
        return currentNote;
    }
}
