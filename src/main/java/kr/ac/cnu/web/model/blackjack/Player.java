package kr.ac.cnu.web.model.blackjack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 21..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String name;
    private long money;

    private List<Integer> cards;
    private long betMoney;


    public void addCard(int i) {
        cards.add(i);
    }

}
