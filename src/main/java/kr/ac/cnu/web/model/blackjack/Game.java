package kr.ac.cnu.web.model.blackjack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Data
public class Game {
    private Dealer dealer;
    private Player player;

    @JsonIgnore
    private Random random = new Random();
    public void init() {
        dealer.setCards(new ArrayList<>());
        dealer.addCard(random.nextInt(9) + 1);

        player.setCards(new ArrayList<>());
        player.addCard(random.nextInt(9) + 1);
        player.addCard(random.nextInt(9) + 1);

    }

    public void hit() {
        player.addCard(random.nextInt(9) + 1);
    }

    public void dealerHit() {
        dealer.addCard(random.nextInt(9) + 1);
    }

}
