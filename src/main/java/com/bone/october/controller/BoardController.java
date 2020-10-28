package com.bone.october.controller;

import com.bone.october.domain.Board;
import com.bone.october.exception.NotFoundException;
import com.bone.october.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/v1/board", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class BoardController {

    private final BoardService service;

    @PostMapping("/create")
    public ResponseEntity<Board> create(Board request) throws Exception {

        Board board = service.create(request);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Board> read(@PathVariable("id") long id) throws Exception {

        Optional<Board> boardOptional = service.read(id);
        Board board = boardOptional.orElseThrow(NotFoundException::new);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Board>> readAll() throws Exception {

        List<Board> boardList = service.readAll();

        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method={ RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Board> update(Board request) throws Exception {

        Board updated = service.update(request);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method={ RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity delete(long id) throws Exception {

        service.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

