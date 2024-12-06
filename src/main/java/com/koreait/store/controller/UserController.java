package com.koreait.store.controller;

import com.koreait.store.dto.UserDTO;
import com.koreait.store.mapper.UserMapper;
import com.koreait.store.service.UserService;
import com.koreait.store.validator.UserValidator;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);
    @Autowired private UserService userService;

    @GetMapping("/login")
    public void get_login() {}
    /************************************************/
    @GetMapping("/join")
    public void get_join(@ModelAttribute UserDTO userDTO) {}

    @PostMapping("/join")
    public String post_join(
            @ModelAttribute @Validated UserDTO userDTO,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if(bindingResult.hasErrors()){
            log.error("에러 발생!");
            log.error(bindingResult.getAllErrors());
            return "user/join";
        }
        //// 이메일 인증번호 확인 여부를 판단한다
        // 인증된 이메일을 가져온다
        String certCompleteEmail = (String) session.getAttribute("emailAuth");
        // 인증을 안하고 join버튼을 눌렀거나 (null), 인증한 이메일과 가입하려고 하는 이메일이 다르다면
        if(Objects.isNull(certCompleteEmail) || !userDTO.getEmail().equals(certCompleteEmail)){
            return "user/join"; // 가입을 못하게. 실패라면 회원가입 화면으로.
        }

        boolean joinResult = userService.join_user(userDTO);
        // 가입 성공이면 login 화면으로, 실패라면 회원가입 화면으로.
        return joinResult ? "redirect:/user/login" : "user/join";
    }
}
