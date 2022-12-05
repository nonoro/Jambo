package jambo.controller;


import com.google.gson.JsonObject;
import jambo.dto.StudyBoardDTO;
import jambo.service.StudyBoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("/StudyBoard")
public class StudyBoardController {

    @Autowired
    private StudyBoardService service;

    @RequestMapping("/StudyBoardMain")
    public String main(){

        return "/StudyBoard/StudyBoardMain";
    }

    @RequestMapping("/StudyBoardWrite")
    public String openWriteForm(){

        return "/StudyBoard/StudyBoardWriteForm";
    }

    @RequestMapping("/insert")
    public String studyBoardInsert(StudyBoardDTO studyBoardDTO) throws IOException{

        service.insert(studyBoardDTO);
        System.out.println("con studyBoardDTO = " + studyBoardDTO);

        return "index";
    }



}
