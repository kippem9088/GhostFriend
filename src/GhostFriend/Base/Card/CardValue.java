package GhostFriend.Base.Card;

public enum CardValue {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, JOKER;

    public static CardValue convertString(String input) {
        if (input.toUpperCase().equals("ACE") || input.toUpperCase().equals("A")) {
            return ACE;
        }
        if (input.toUpperCase().equals("TWO") || input.toUpperCase().equals("2")) {
            return TWO;
        }
        if (input.toUpperCase().equals("THREE") || input.toUpperCase().equals("3")) {
            return THREE;
        }
        if (input.toUpperCase().equals("FOUR") || input.toUpperCase().equals("4")) {
            return FOUR;
        }
        if (input.toUpperCase().equals("FIVE") || input.toUpperCase().equals("5")) {
            return FIVE;
        }
        if (input.toUpperCase().equals("SIX") || input.toUpperCase().equals("6")) {
            return SIX;
        }
        if (input.toUpperCase().equals("SEVEN") || input.toUpperCase().equals("7")) {
            return SEVEN;
        }
        if (input.toUpperCase().equals("EIGHT") || input.toUpperCase().equals("8")) {
            return EIGHT;
        }
        if (input.toUpperCase().equals("NINE") || input.toUpperCase().equals("9")) {
            return NINE;
        }
        if (input.toUpperCase().equals("TEN") || input.toUpperCase().equals("10")) {
            return TEN;
        }
        if (input.toUpperCase().equals("JACK") || input.toUpperCase().equals("J")) {
            return JACK;
        }
        if (input.toUpperCase().equals("QUEEN") || input.toUpperCase().equals("Q")) {
            return QUEEN;
        }
        if (input.toUpperCase().equals("KING") || input.toUpperCase().equals("K")) {
            return KING;
        }

        return null;
    }

    public static int toInteger(CardValue cardValue) {
        switch (cardValue) {
            case ACE:
                return 14;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case JACK:
                return 11;
            case QUEEN:
                return 12;
            case KING:
                return 13;
            case JOKER:
                return 15;
        }

        return 0;
    }
}
