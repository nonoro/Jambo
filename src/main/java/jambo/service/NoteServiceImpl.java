package jambo.service;

import jambo.domain.user.Note;
import jambo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    /**
     * 받은 쪽지함 조회
     */
    @Override
    public List<Note> selectAll(String email) {
        return noteRepository.findNotesByReceiveUser(email);
    }

    /**
     * 쪽지 전송
     */
    @Override
    public void insert(Note note) {
        Note dbNote = noteRepository.save(note);
    }
}
