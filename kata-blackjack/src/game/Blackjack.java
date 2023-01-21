package game;

import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.Player;

public class Blackjack {
    
    public static boolean isBlackJack(List<Card> bet) {
        return getBetSum(bet) == 21 && bet.size() == 2;
    }

    public static boolean isBust(List<Card> bet) {
        return getBetSum(bet) > 21;
    }
    
    public static int getBetSum(List<Card> bet) {
        int sum = 0;
        int aces = 0;
    
        for (Card card: bet) {
            sum += card.value();
            if (card.isAce()) aces++;

        }
        
        while ((sum > 21) && (aces > 0)) {
            sum -= 10;
            aces--;
        }
        return sum;
    }
    
    public static List<Player> getWinners(Player player1, Player player2, Player player3, Player croupier, List<Card> deck) {
        
        List<Player> winners = new ArrayList<>();
        Player[] players = {player1, player2, player3};
        
        while (getBetSum(croupier.getBet()) < 17) {
            croupier.addCard(deck.remove(0));
        }
        
        if (isBlackJack(croupier.getBet())) return winners;
        
        if (getBetSum(croupier.getBet()) > 21) {
            for (Player player: players) {
                if (getBetSum(player.getBet()) <= 21) winners.add(player);
            }
            return winners;
        }
        
        for (Player player: players) {
            if (player.isWinner(croupier)) winners.add(player);
        }
        
        return winners;
    }
}