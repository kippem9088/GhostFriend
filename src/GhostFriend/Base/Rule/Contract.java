package GhostFriend.Base.Rule;

import GhostFriend.Base.Card.CardSuit;

public class Contract {
    public CardSuit getGiru() {
        return giru;
    }

    public boolean isEquals(Contract contract) {
        return ((this.getGiru() == contract.getGiru()) && (this.getScore() == contract.getScore()));
    }

    private CardSuit giru;

    public Integer getScore() {
        return score;
    }

    private Integer score;

    public Boolean getDeclared() {
        return isDeclared;
    }

    private Boolean isDeclared;

    public void initialize() {
        this.giru = null;
        this.score = -1;
        this.isDeclared = false;
    }

    public void declare(CardSuit suit, Integer score) {
        this.giru = suit;
        this.score = score;
        this.isDeclared = true;
    }

    public void declare(Contract contract) {
        this.giru = contract.giru;
        this.score = contract.score;
        this.isDeclared = true;
    }

    public static Boolean isValidGiru(Contract contract) {
        return (contract.giru != CardSuit.JOKER);
    }

    public String toString(String delimiter) {
        if (this.isDeclared) {
            if (this.giru == null) {
                return "No giru" + " " + this.score.toString();
            } else {
                return this.giru.toString() + delimiter + this.score.toString();
            }
        } else {
            return "선언되지 않았습니다.";
        }
    }

    public Contract() {
        initialize();
    }
}
