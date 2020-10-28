package com.bone.october.service;

import com.bone.october.domain.Board;
import com.bone.october.exception.NotFoundException;
import com.bone.october.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    final private BoardRepository boardRepository;

    @Transactional
    public Board  create(Board board) {
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Optional<Board> read(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Board> readAll() {
        return boardRepository.findAll();
    }

    @Modifying
    @Transactional
    public Board  update(Board request) throws Exception {

        Optional<Board> boardOptional = this.read(request.getId());
        Board board = boardOptional.orElseThrow(NotFoundException::new);

        board.setContents(request.getContents());
        board.setTitle(request.getTitle());

        return boardRepository.save(board);
    }

    @Modifying
    @Transactional
    public void  delete(Long id) {
        boardRepository.deleteById(id);
    }
}
