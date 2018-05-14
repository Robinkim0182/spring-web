package kr.ac.cnu.web.controller;

import kr.ac.cnu.web.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 14..
 */
@Controller
public class BoardController {
    private List<Board> boards = Arrays.asList(
            new Board(1, "제목1", "1번 게시물의 내용입니다.", "ian"),
            new Board(2, "제목2", "2번 이에요", "ian"),
            new Board(3, "제목3", "3번?", "ian"),
            new Board(4, "제목4", "4444", "ian")
    );

    @GetMapping("/boards")
    public String getBoards(Model model) {
        model.addAttribute("boards", boards);

        return "boards";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable long id, Model model) {
        Board findOne = null;

        for (Board board : boards) {
            if (board.getId() == id) {
                findOne = board;
                break;
            }
        }

        model.addAttribute("board", findOne);

        return "board";
    }
}
