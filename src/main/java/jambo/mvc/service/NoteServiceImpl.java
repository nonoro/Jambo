package jambo.mvc.service;

import jambo.domain.user.Note;
import jambo.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    /**
     * 받은 쪽지함 조회
     */
    @Override
    public List<Note> selectAll(String email) {
        return noteRepository.findNotesByReceiveUser(email); //받은 사람 이메일로 조회
    }

    /**
     * 쪽지 전송 : 쪽지 전송 버튼을 누르면 dbNote에 note들이 저장된다.
     */
    @Override
    public void insert(Note note) {
        Note dbNote = noteRepository.save(note);
    }
}
