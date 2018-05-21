package kr.ac.cnu.web.controller.api;

import kr.ac.cnu.web.model.blackjack.Game;
import kr.ac.cnu.web.model.blackjack.Player;
import kr.ac.cnu.web.service.BlackjackService;
import kr.ac.cnu.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by rokim on 2018. 5. 21..
 */
@RestController
@RequestMapping("/api/black-jack")
public class BlackApiController {
    @Autowired
    private BlackjackService blackjackService;

    @PostMapping("/init/{name}")
    public Player init(HttpSession httpSession, @PathVariable String name) {
        Player player = new Player();
        player.setName(name);
        player.setMoney(10000);
        httpSession.setAttribute("player", player);
        httpSession.setAttribute("game", new GameService());

        return player;
    }


    @Autowired
    private GameService gameService;

    @PostMapping("/deal")
    public Player deal(HttpSession httpSession, @RequestBody long betMoney) {
        if (httpSession.getAttribute("player") == null) {
            return null;
        }

        Player player = (Player) httpSession.getAttribute("player");
        player.setMoney(player.getMoney() - betMoney);
        player.setBetMoney(betMoney);

        gameService.addPlay(player);

        return player;
    }

    @GetMapping("/playing")
    public Game playing() {
        return gameService.getGame();
    }

    @PostMapping("/hit")
    public Game hit() {
        return gameService.hit();
    }

    @PostMapping("/stand")
    public Game stand() {
        return gameService.stand();
    }

}
