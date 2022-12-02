package jambo.mvc.service;

import jambo.domain.user.Note;

import java.util.List;

public interface NoteService {

    /**
     * 받은 쪽지함 조회
     */
    List<Note> selectAll(String email);

    /**
     * 쪽지 전송
     */
    void insert(Note note);
}
