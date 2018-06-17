package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NoSuchRankException;
import lombok.Data;

/**
 * Created by rokim on 2018. 5. 26..
 */
@Data
public class Card {
    private final int rank;
    private final Suit suit;
    private int shape=0;

    public Card(int rank, Suit suit) {
        if (rank > 13) {
            throw new NoSuchRankException();
        }
        if(rank == 11){
            this.rank = 10;
            this.suit = suit;
            this.shape = 1;
        }
        else if(rank == 12){
            this.rank = 10;
            this.suit = suit;
            this.shape = 2;
        }
        else if(rank == 13){
            this.rank = 10;
            this.suit = suit;
            this.shape = 3;
        }
        else{
            this.rank = rank;
            this.suit = suit;
        }
    }
}
