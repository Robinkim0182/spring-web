package kr.ac.cnu.web.controller;

import kr.ac.cnu.web.model.Board;
import kr.ac.cnu.web.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 14..
 */
@Controller
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;


    @GetMapping("/boards")
    public String getBoards(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        return "boards";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable long id, Model model) {
        Board board = boardRepository.findById(id).get();
        model.addAttribute("board", board);

        return "board";
    }

    @PostMapping("/boards")
    public void addBoard(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String writerId) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setWriterId(writerId);

        boardRepository.save(board);
    }
}
