package com.example.demomongo.service;

import com.example.demomongo.domain.Board;
import com.example.demomongo.dto.BoardSaveDto;
import com.example.demomongo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void upsert(BoardSaveDto dto, String id) {
        Board board = dto.toEntity();
        log.info("upsert id={}, dto={}", id, dto.toString());
        log.info("board={}", board.toString());
        if (id.isBlank()) {
            log.info("update id={}", id);
            board.setId(id); // save함수는 같은 아이디면 수정한다.
        }
        boardRepository.save(board);
    }

    public void deleteById(String id) {
        log.info("deleteById id={}", id);
        boardRepository.deleteById(id); // 내부적으로 실행되다가 오류 Exception 발동
    }

    public Board findById(String id) {
        log.info("findById id={}", id);
        return boardRepository.findById(id).orElse(null);
    }

    public Board findByTitle(String title) {
        log.info("findByTitle title={}", title);
        return boardRepository.findByTitle(title);
    }

    public Board findByContent(String content) {
        log.info("findByContent content={}", content);
        return boardRepository.findByContent(content);
    }

    public List<Board> findAll() {
        log.info("findAll");
        return boardRepository.findAll();
    }

    public Board save(BoardSaveDto dto) {
        log.info("save dto={}", dto.toString());
        return boardRepository.save(dto.toEntity()); // MessageConverter 발동 -> 자바오브제그를 Json 변환해서 응답함.
    }
}
