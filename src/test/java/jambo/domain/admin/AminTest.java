package jambo.domain.admin;

import jambo.controller.AdminController;
import jambo.dto.AdminJoinDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class AminTest {
    @Autowired
    private AdminController adminController;

    @Test
    public void adminCreateTest(){
        AdminJoinDTO adminJoinDTO = new AdminJoinDTO("hello", "123", "종영", "관리자종영");
        adminController.join(adminJoinDTO);
    }
}
