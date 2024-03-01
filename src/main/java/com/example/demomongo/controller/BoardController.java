package com.example.demomongo.controller;

import com.example.demomongo.domain.Board;
import com.example.demomongo.dto.BoardSaveDto;
import com.example.demomongo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 생성 수정
     */
    @PutMapping("/board/{id}")
    public void upsert(@RequestBody BoardSaveDto dto, @PathVariable String id) {
        log.info("update id={}, dto={}", id, dto);
        boardService.upsert(dto, id);
    }

    @DeleteMapping("/board/{id}")
    public void deleteById(@PathVariable String id) {
        log.info("deleteById id={}", id);
        boardService.deleteById(id);
    }

    @GetMapping("/board/id/{id}")
    public Board findById(@PathVariable String id) {
        log.info("findById id={}", id);
        return boardService.findById(id);
    }

    @GetMapping("/board/title/{title}")
    public Board findByTitle(@PathVariable String title) {
        log.info("findByTitle title={}", title);
        return boardService.findByTitle(title);
    }

    @GetMapping("/board/content/{content}")
    public Board findByContent(@PathVariable String content) {
        log.info("findByContent content={}", content);
        return boardService.findByContent(content);
    }

    @GetMapping("/boards")
    public List<Board> findAll() { // 리턴을 JavaObject로 하면 스프링 내부적으로 JSON으로 자동 변환 해준다.
        log.info("findAll");
        return boardService.findAll();
    }

    @PostMapping("/board")
    public Board save(@RequestBody BoardSaveDto dto) { // {"title":"제목3","content":"내용3"}
        log.info("save dto={}", dto.toString());
        return boardService.save(dto);
    }
}
