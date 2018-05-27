package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Dealer {
    @Getter
    private Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }


    public void reset() {
        hand.reset();
    }

    public void deal() {
        hand.drawCard();
    }

    public void play() {
        while(hand.getCardSum() < 17) {
            hand.drawCard();
        }
    }
}
