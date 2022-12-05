package jambo.service;

import jambo.domain.user.Note;
import java.util.List;

public interface NoteService {

    List<Note> selectAll(String email);

    void insert(Note note);
}
