package jambo.service;

import jambo.domain.Alarm;
import jambo.domain.user.Note;
import jambo.domain.user.User;
import jambo.dto.AlarmResponse;
import jambo.repository.AlarmRepository;
import jambo.repository.NoteRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {
    private final NoteRepository noteRepository;

    private final AlarmRepository alarmRepository;

    private final UserRepository userRepository;

    private final AlarmService alarmService;



    /**
     * 받은 쪽지함
     */
    public Page<Note> selectAll(String email, Pageable page) {
        return noteRepository.findNotesByReceiveUserOrderBySentTimeDesc(email, page);
    }

    /**
     * 상세보기
     */
    public Note selectBy(Long id, int isRead) {
        Note note = noteRepository.findById(id).get();

        if (isRead == 1) {
            Alarm alarm = alarmRepository.findByNote(note).get();
            alarm.setRead(true);
        }

        return note;
    }

    /**
     * 쪽지 전송
     */
    public void insert(Note note, User user) {
        note.setSendUser(user.getEmail());
        note.setNickName(user.getNickName());
        noteRepository.save(note);

        String receiveUser = note.getReceiveUser();

        User dbReceiveUser = userRepository.findByEmail(receiveUser).get();

        alarmRepository.save(new Alarm(dbReceiveUser, note));

        String receiveUserEmail = note.getReceiveUser();
        User receivedUser = userRepository.findByEmail(receiveUserEmail).orElseThrow(() -> {
            throw new UsernameNotFoundException(receiveUserEmail + " 유저가 없습니다.");
        });

        alarmService.send(receivedUser.getId(), AlarmResponse.note(user.getEmail() + " 님에게 쪽지가 왔습니다!!"));
    }

    /**
     * 삭제
     */
    public void delete(Long id) {
        Note note = noteRepository.findById(id).get();
        alarmRepository.deleteByNote(note);
        System.out.println("여기여기여기에~~~~~~~~~~~~~~₩");
        noteRepository.deleteById(id);


    }

}
