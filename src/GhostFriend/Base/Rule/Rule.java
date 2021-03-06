package GhostFriend.Base.Rule;

import GhostFriend.Base.Card.Card;
import GhostFriend.Base.Card.CardSuit;
import GhostFriend.Base.Card.CardValue;
import GhostFriend.Base.Player.Player;

import java.util.List;

public class Rule {
    private Card Mighty;
    private Card JokerCall;
    private CardSuit giru;

    public Integer getMinContractScore() {
        return minContractScore;
    }

    private Integer minContractScore;

    public void setGiru(CardSuit giru) {
        this.giru = giru;
        setMighty(giru);
        setJokerCall(giru);
    }

    public Card getMighty() {
        return Mighty;
    }

    private void setMighty(CardSuit giru) {
        if (giru == CardSuit.SPADE) {
            Mighty = new Card(CardSuit.DIAMOND, CardValue.ACE);
        } else {
            Mighty = new Card(CardSuit.SPADE, CardValue.ACE);
        }
    }

    public Card getJokerCall() {
        return JokerCall;
    }

    private void setJokerCall(CardSuit giru) {
        if (giru == CardSuit.CLUB) {
            JokerCall = new Card(CardSuit.HEART, CardValue.THREE);
        } else {
            JokerCall = new Card(CardSuit.CLUB, CardValue.THREE);
        }
    }

    public Integer getScore(Card card) {
        if (card.IsScoreCard()) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isWinner(Player declarer) {
        return (declarer.getScore() >= declarer.getContract().getScore());
    }

    public boolean isWinnerCard(Card currentWinnerCard, Card submittedCard) {
        if (submittedCard.equals(Mighty)) {
            return true;
        }

        if (currentWinnerCard.equals(Mighty)) {
            return false;
        }

        if (submittedCard.equals(new Card(CardSuit.JOKER, CardValue.JOKER))) {
            return true;
        }

        if (currentWinnerCard.equals(new Card(CardSuit.JOKER, CardValue.JOKER))) {
            return false;
        }

        if ((currentWinnerCard.getCardSuit() == giru) && (submittedCard.getCardSuit() == giru)) {
            return (CardValue.toInteger(submittedCard.getCardValue()) > CardValue.toInteger(currentWinnerCard.getCardValue()));
        }

        if (submittedCard.getCardSuit() == giru) {
            return true;
        }

        if (currentWinnerCard.getCardSuit() == giru) {
            return false;
        }

        if (currentWinnerCard.getCardSuit() != submittedCard.getCardSuit()) {
            return false;
        }

        return (CardValue.toInteger(submittedCard.getCardValue()) > CardValue.toInteger(currentWinnerCard.getCardValue()));
    }

    private double getDealScore(Card card) {
        if (card.getCardValue() == CardValue.TEN) {
            return 0.5;
        }
        else if ((card.getCardValue() == CardValue.ACE) ||
                (card.getCardValue() == CardValue.JACK) ||
                (card.getCardValue() == CardValue.QUEEN) ||
                (card.getCardValue() == CardValue.KING)) {
            return 1.0;
        }
        else if (card.getCardValue() == CardValue.JOKER) {
            return -1.0;
        } else {
            return 0.0;
        }
    }

    public static Integer getNumOfCardsPerPerson() {
        return 10; // ToDo: I'm only considering the case that 5 players come in
    }

    public ContractValidation isValidContract(Contract currentContract, Contract newContract) {
        if (newContract.getScore() < this.minContractScore) {
            return ContractValidation.MINIMUM;
        }
        else if (newContract.getScore() <= currentContract.getScore()) {
            return ContractValidation.SCORE;
        }
        else if (!Contract.isValidGiru(newContract)) {
            return ContractValidation.GIRU;
        } else {
            return ContractValidation.VALID;
        }
    }

    public void decreaseMinContractScore() {
        this.minContractScore--;
    }

    public Boolean isDealMiss(List<Card> cardList) {

        double score = 0.0;

        for (Card card: cardList) {
            score += getDealScore(card);
        }

        return (score < 2.0);
    }

    public Rule() {
        this.minContractScore = 13;
    }

}
