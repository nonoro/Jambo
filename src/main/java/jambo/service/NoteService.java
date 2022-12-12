package jambo.service;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import jambo.repository.NoteRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    /**
     * 받은 쪽지함
     */
    public Page<Note> selectAll(String email, Pageable page) {
        if(email ==null){
            return noteRepository.findAllByOrderBySentTimeDesc(email, page);
        }
        return noteRepository.findNotesByReceiveUser(email, page);
    }

    /**
     * 상세보기
     */
    public Note selectBy(Long id) {
        Note note = noteRepository.findById(id).get();
        return note;
    }

    /**
     * 쪽지 전송
     */
    public void insert(Note note, User user) {
//        note.setSendUser(note.getSendUser());
        note.setSendUser(user);
        Note dbNote = noteRepository.save(note);
    }

    /**
     * 삭제
     */
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

}
