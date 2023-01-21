package test;

import org.junit.Test;
import static test.BlackJackTest.Card.*;
import static org.junit.Assert.assertEquals;

public class BlackJackTest{
         
    @Test
    public void test_hand_value_with_one_card(){
        assertEquals(3, createHand(_3).value());
        assertEquals(10, createHand(_10).value());
        assertEquals(10, createHand(Jack).value());
        assertEquals(10, createHand(Queen).value());
        assertEquals(10, createHand(King).value());
        assertEquals(11, createHand(Ace).value());
    }
    
    @Test
    public void test_hand_value_with_two_cards() {
        assertEquals(8, createHand(_3, _5).value());        
    }
    
    @Test
    public void test_hand_is_black_jack() {
        assertEquals(false, createHand(_3, _5).isBlackJack());        
        assertEquals(true, createHand(Ace, Jack).isBlackJack());
        assertEquals(true, createHand(Ace, King).isBlackJack());
        assertEquals(true, createHand(Ace, Queen).isBlackJack());
        assertEquals(true, createHand(_10, Ace).isBlackJack());        
    }
    
    @Test
    public void given_one_card_should_calculate_value() {
       assertEquals(5, createHand(_5).value());
       assertEquals(6, createHand(_6).value());
       assertEquals(10, createHand(Jack).value());
       assertEquals(10, createHand(Queen).value());
       assertEquals(10, createHand(King).value());
       assertEquals(11, createHand(Ace).value());
    }
    
    @Test
    public void given_two_cards_should_calculate_value() {
       assertEquals(11, createHand(_5, _6).value());        
       assertEquals(12, createHand(Ace, Ace).value());        
    }
    
    @Test 
    public void given_two_cards_should_determine_if_is_black_jack() {
       assertEquals(false, createHand(_5, _6).isBlackJack());        
       assertEquals(true, createHand(Ace, Queen).isBlackJack());               
    }

    @Test 
    public void given_three_cards_should_determine_that_is_not_black_jack() {
       assertEquals(false, createHand(_5, _6, Queen).isBlackJack());               
    }
    
    @Test 
    public void given_two_cards_should_determine_that_is_not_bust() {
       assertEquals(false, createHand(_4,_3).isBust());               
    }
    
    @Test 
    public void given_three_cards_should_determine_that_is_bust_or_not() {
       assertEquals(true, createHand(_4, Jack, King).isBust());               
       assertEquals(false, createHand(_4, _2, _3).isBust());               
    }
    
    @Test 
    public void given_one_card_should_determine_that_is_face_or_not() {
       assertEquals(false, Card._2.isFace());    
       assertEquals(false, Card._3.isFace()); 
       assertEquals(false, Card.Ace.isFace()); 
       assertEquals(true, Card.King.isFace()); 
       assertEquals(true, Card.Queen.isFace()); 
    }
    

    private Hand createHand(Card... cards) {
        return new Hand() {

            @Override
            public int value() {
                int sum = 0;
                for (Card card : cards)
                    sum += card.value();
                return sum;
            }

            @Override
            public boolean isBlackJack() {
                return containsCardWithValue(11) && containsCardWithValue(10);
            }

            private boolean containsCardWithValue(int expectedValue) {
                for (Card card : cards)
                    if (card.value() == expectedValue) return true;
                return false;
                
            }
            
            @Override
            public boolean isBust() {
                return value() > 21;
            }

        };
    }

    public interface Hand {
        public int value();
        public boolean isBlackJack();
        public boolean isBust();
    }
    
    public enum Card {
        _2, _3, _4, _5, _6, _7, _8, _9, _10, Jack, Queen, King, Ace;

        private boolean isFace() {
            return this == King || this == Queen || this == Jack;
        }

        private boolean isAce() {
            return this == Ace;
        }

        private int value() {
            if (isAce()) return 11;
            if (isFace()) return 10;
            return ordinal() + 2;
        }
    }
    
    
    
}
