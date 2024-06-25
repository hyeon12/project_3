package org.choongang.member.controllers;

import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.RequestMapping;

@Controller
@RequestMapping("/member")//앞의 주소 member 로 맵핑
public class MemberController {

    //회원 가입 양식
    @GetMapping("/join")
    public String join(){
        return "member/join";
    }

    //회원 가입 처리
    @GetMapping("/join")
    public String joinPs(){
        return null;
    }

    //로그인 양식
    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    //로그인 처리
    @GetMapping("/login")
    public String loginPs(){
        return null;
    }
}