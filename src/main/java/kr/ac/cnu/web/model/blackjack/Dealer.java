package kr.ac.cnu.web.model.blackjack;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Data
public class Dealer {
    private List<Integer> cards = new ArrayList<>();

    public void addCard(int i) {
        cards.add(i);
    }
}
