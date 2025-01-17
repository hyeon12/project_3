package org.choongang.board.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.board.entities.Board;
import org.choongang.board.exceptions.BoardConfigNotFoundException;
import org.choongang.board.services.config.BoardConfigInfoService;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.PathVariable;
import org.choongang.global.config.annotations.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final HttpServletRequest request;
    private final BoardConfigInfoService configInfoService; //게시판 조회 서비스


    //게시판 목록
    @GetMapping("/list/{bId}")
    public String list(@PathVariable("bId") String bId){
        commonProcess(bId, "list");
        return "board/list";//템플릿 연동 해야 함
    }

    //게시글 보기
    @GetMapping("/view/{seq}")
    public String view(@PathVariable("seq") long seq){

        return "board/view";
    }

    @GetMapping("/write/{bId}")
    public String write(@PathVariable("bId") String bId) {
        commonProcess(bId, "write");

        RequestBoardData data = new RequestBoardData();
        data.setBId(bId);//의존성 추가로 getter,setter

        request.setAttribute("data", data);//기초 초기 데이터 넣어주기

        return "board/write";
    }

    @GetMapping("/update/{seq}")
    public String update(@PathVariable("seq") long seq){
        return "board/update";
    }

    /**
     * 모든 요청 처리 메서드의 공통 처리 부분
     * @param bId : 게시판 설정 유지 / 게시판 설정 X -> 게시판이 없음(BoardConfigNotFoundException)
     * @param mode : 처리 모드 - write, update, list, view */
    private void commonProcess(String bId, String mode){
        Board board = configInfoService.get(bId).orElseThrow(BoardConfigNotFoundException::new);

        //mode가 null이면 write로 기본값 설정
        mode = Objects.requireNonNullElse(mode, "write");

        List<String> addCss = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        addCss.add("board/style");// 모든 게시판의 공통 스타일

        if(mode.equals("write") || mode.equals("update")){// 쓰기, 수정
            addCss.add("board/form"); //양식 추가
            addScript.add("ckeditor5/ckeditor");
            //js 패키지 -> ckeditor5 -> ckeditor.js 찾음
            addScript.add("board/form");
        } else if(mode.equals("list")){// 목록
            addCss.add("board/list");

        } else if(mode.equals("view")){// 글보기
            addCss.add("board/view");
        }

        request.setAttribute("board", board);
        request.setAttribute("addCss", addCss);
        request.setAttribute("addScript", addScript);
        //css, js 추가
    }

    @GetMapping("/notice")
    public String notice(){
        request.setAttribute("addCss", List.of("notice"));
        return "board/notice";
    }
}
