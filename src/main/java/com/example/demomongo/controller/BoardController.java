package com.example.demomongo.controller;

import com.example.demomongo.domain.Board;
import com.example.demomongo.dto.BoardSaveDto;
import com.example.demomongo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;

    @PutMapping("board/{id}")
    public void update(@RequestBody BoardSaveDto dto, @PathVariable String id) {

        Board board = dto.toEntity();
        board.set_id(id); // save함수는 같은 아이디면 수정한다.

        boardRepository.save(board);
    }

    @DeleteMapping("board/{id}")
    public int deleteById(@PathVariable String id) {
        boardRepository.deleteById(id); // 내부적으로 실행되다가 오류 Exception 발동
        return 1; // 1 : 성공, -1 : 실패
    }

    @GetMapping("/board/{id}")
    public Board findById(@PathVariable String id) {
        return boardRepository.findById(id).get();
    }

    @GetMapping("/board")
    public List<Board> findAll() { // 리턴을 JavaObject로 하면 스프링 내부적으로 JSON으로 자동 변환 해준다.
        return boardRepository.findAll();
    }

    @PostMapping("/board")
    public Board save(@RequestBody BoardSaveDto dto) { // {"title":"제목3","content":"내용3"}
        Board boardEntity = boardRepository.save(dto.toEntity());
        return boardEntity; // MessageConverter 발동 -> 자바오브제그를 Json 변환해서 응답함.
    }
}
