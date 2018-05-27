package kr.ac.cnu.web.controller.api;

import kr.ac.cnu.web.exceptions.NoLoginException;
import kr.ac.cnu.web.games.blackjack.GameRoom;
import kr.ac.cnu.web.model.User;
import kr.ac.cnu.web.service.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.awt.*;

/**
 * Created by rokim on 2018. 5. 21..
 */
@RestController
@RequestMapping("/api/black-jack")
public class BlackApiController {
    @Autowired
    private BlackjackService blackjackService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User login(HttpSession httpSession, @RequestBody String name) {
        User user = new User(name, 100000L);
        httpSession.setAttribute("user", user);

        return user;
    }

    @PostMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
    }

    @PostMapping("/rooms")
    public GameRoom createRoom(HttpSession httpSession) {
        User user = this.getUserFromSession(httpSession);

        return blackjackService.createGameRoom(user);
    }

    @PostMapping(value = "/rooms/{roomId}/bet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GameRoom bet(HttpSession httpSession, @PathVariable String roomId, @RequestBody long betMoney) {
        User user = this.getUserFromSession(httpSession);

        return blackjackService.bet(roomId, user, betMoney);
    }

    @PostMapping("/rooms/{roomId}/hit")
    public GameRoom hit(HttpSession httpSession, @PathVariable String roomId) {
        User user = this.getUserFromSession(httpSession);

        return blackjackService.hit(roomId, user);
    }

    @PostMapping("/rooms/{roomId}/stand")
    public GameRoom stand(HttpSession httpSession, @PathVariable String roomId) {
        User user = this.getUserFromSession(httpSession);

        return blackjackService.stand(roomId, user);
    }

    @GetMapping("/rooms/{roomId}")
    public GameRoom getGameRoomData(@PathVariable String roomId) {
        return blackjackService.getGameRoom(roomId);
    }


    private User getUserFromSession(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            throw new NoLoginException();
        }

        return (User) httpSession.getAttribute("user");
    }
}
