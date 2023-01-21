package model;

import game.Blackjack;
import java.util.List;

public class Player {
    private final List<Card> bet;
    
    public Player(List<Card> bet) {
        this.bet = bet;
    }
    
    public List<Card> getBet() {
        return bet;
    }
    
    public void addCard(Card card) {
        bet.add(card);
    }
    
    public boolean isWinner(Player croupier) {
        int sumBetCroupier = Blackjack.getBetSum(croupier.getBet());
        int sumBetPlayer = Blackjack.getBetSum(this.bet);
        
        return (sumBetPlayer > sumBetCroupier) && (sumBetPlayer <= 21) || Blackjack.isBlackJack(this.bet);
    }
}   