package org.choongang.member.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Service;
import org.choongang.global.exceptions.AlertException;
import org.choongang.member.constants.UserType;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.validators.JoinValidator;
import org.mindrot.jbcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class JoinService {
    //의존성 정의
    private final JoinValidator validator;
    private final MemberMapper mapper;

    public void process(RequestJoin form){
        validator.check(form);// 회원가입 -> 회원 데이터 검증

        // 비밀번호 해시화 -> 비밀번호가 뭔지 알 필요X, 일치여부만 파악하면 됨.
        String hash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));

        // DB에 영구 저장 처리
        Member member = Member.builder()
                .email(form.getEmail())
                .password(hash)
                .userName(form.getUserName())
                .userType(UserType.USER)
                .build();

        int result = mapper.register(member);
        if(result < 1){
            throw new AlertException("회원가입에 실패하였습니다.", HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
