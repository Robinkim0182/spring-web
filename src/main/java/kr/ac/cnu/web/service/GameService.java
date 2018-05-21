package kr.ac.cnu.web.service;

import kr.ac.cnu.web.model.blackjack.Dealer;
import kr.ac.cnu.web.model.blackjack.Game;
import kr.ac.cnu.web.model.blackjack.Player;
import org.springframework.stereotype.Service;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Service
public class GameService {
    private Game game;

    public Game addPlay(Player player) {
        game = new Game();

        Dealer dealer = new Dealer();
        game.setDealer(dealer);
        game.setPlayer(player);

        game.init();

        return game;
    }

    public Game getGame() {
        return game;
    }

    public Game hit() {
        game.hit();
        return game;
    }

    public Game stand() {
        while(game.getDealer().getCards().stream().mapToInt(value -> value.intValue()).sum() < 17) {
            game.dealerHit();
        }

        if (game.getPlayer().getCards().stream().mapToInt(value -> value.intValue()).sum() < 22){
            if (game.getDealer().getCards().stream().mapToInt(value -> value.intValue()).sum() > 21) {
                game.getPlayer().setMoney(game.getPlayer().getMoney() + game.getPlayer().getBetMoney());
            } else if (game.getPlayer().getCards().stream().mapToInt(value -> value.intValue()).sum() >
                    game.getDealer().getCards().stream().mapToInt(value -> value.intValue()).sum()) {
                game.getPlayer().setMoney(game.getPlayer().getMoney() + game.getPlayer().getBetMoney());
            }
        }

        return game;
    }

}
