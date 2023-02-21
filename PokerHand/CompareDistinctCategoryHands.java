import java.nio.file.Files;
import java.nio.file.Paths;

public class CompareDistinctCategoryHands {
    public static void main(String[] args) {
        int p1wins = 0;
        int p2wins = 0;
        Integer max1;
        Integer max2;
        int[] compareValues;
        int valueAtKey;

        try {
            for (String line : Files.readAllLines(Paths.get("/Volumes/DATA/Java/CIS2168/Hwk9/distinctHandPairs.txt"))) {
                Hand hand1 = new Hand(line.substring(0, 14));
                Hand hand2 = new Hand(line.substring(14, 29).trim());

                if (hand1.getHandValue() > hand2.getHandValue()) {
                    System.out.println("Player 1: " + hand1 + "  (" + Hand.nameMap.get(hand1.getHandValue()) +
                            ")\nPlayer 2: " + hand2 +  "  (" + Hand.nameMap.get(hand2.getHandValue()) +
                            ")\n\t\t\t    Player 1 wins.\n");
                    p1wins++;
                } else if (hand1.getHandValue() < hand2.getHandValue()) {
                    System.out.println("Player 1: " + hand1 + "  (" + Hand.nameMap.get(hand1.getHandValue()) +
                            ")\nPlayer 2: " + hand2 + "  (" + Hand.nameMap.get(hand2.getHandValue()) +
                            ")\n\t\t\t    Player 2 wins.\n");
                    p2wins++;
                } else {
                    compareValues = new int[2];
                    // Straight Flush
                    max1 = hand1.getCardRanks()[0];
                    max2 = hand2.getCardRanks()[0];
                    if (max1 > max2) compareValues[0] = max1;
                    else if (max2 > max1) compareValues[1] = max2;

                        // same four-of-a-kind
                    else if (hand1.getHandValue() == 7) {

                        for (Integer key : hand1.getCardRanks()) {

                            valueAtKey = hand1.getRankFrequency().get(key);
                            if (valueAtKey == 4) { compareValues[0] = key; }
                        }
                        for (Integer key : hand2.getCardRanks()) {
                            valueAtKey = hand2.getRankFrequency().get(key);
                            if (valueAtKey == 4) { compareValues[1] = key; }
                        }
                    }

                    // same full-house
                    else if (hand1.getHandValue() == 6) {
                        for (Integer key : hand1.getCardRanks()) {
                            valueAtKey = hand1.getRankFrequency().get(key);
                            if (valueAtKey == 3) { compareValues[0] = key; }
                        }
                        for (Integer key : hand2.getCardRanks()) {
                            valueAtKey = hand2.getRankFrequency().get(key);
                            if (valueAtKey == 3) { compareValues[1] = key; }
                        }
                    }

                    // same flush
                    else if (hand1.getHandValue() == 5) {
                        for (Integer key : hand1.getCardRanks()) {
                            valueAtKey = hand1.getRankFrequency().get(key);
                            if (valueAtKey == 5) { compareValues[0] = key; }
                        }
                        for (Integer key : hand2.getCardRanks()) {
                            valueAtKey = hand2.getRankFrequency().get(key);
                            if (valueAtKey == 5) { compareValues[1] = key; }
                        }
                    }

                    // same straight
                    else if (hand1.getHandValue() == 4) {
                        max1 = hand1.getCardRanks()[0];
                        max2 = hand2.getCardRanks()[0];
                        if (max1 > max2) compareValues[0] = max1;
                        else if (max2 > max1) compareValues[1] = max2;
                    }

                    // same three of a kind
                    else if (hand1.getHandValue() == 3) {
                        for (Integer key : hand1.getCardRanks()) {
                            valueAtKey = hand1.getRankFrequency().get(key);
                            if (valueAtKey == 3) { compareValues[0] = key; }
                        }
                        for (Integer key : hand2.getCardRanks()) {
                            valueAtKey = hand2.getRankFrequency().get(key);
                            if (valueAtKey == 3) { compareValues[1] = key; }
                        }
                    }

                    // same two pairs:
                    else if (hand1.getHandValue() == 2) {
                        for (Integer key : hand1.getCardRanks()) {
                            valueAtKey = hand1.getRankFrequency().get(key);
                            if (valueAtKey == 2 && key > compareValues[0]) { compareValues[0] = (key); }
                        }
                        for (Integer key : hand2.getCardRanks()) {
                            valueAtKey = hand2.getRankFrequency().get(key);
                            if (valueAtKey == 2 && key > compareValues[0]) { compareValues[1] = (key); }
                        }
                    }

                    // same pair
                    else if (hand1.getHandValue() == 1) {
                        for (Integer key : hand1.getCardRanks()) {
                            valueAtKey = hand1.getRankFrequency().get(key);
                            if (valueAtKey == 2) { compareValues[0] = (key); }
                        }
                        for (Integer key : hand2.getCardRanks()) {
                            valueAtKey = hand2.getRankFrequency().get(key);
                            if (valueAtKey == 2) { compareValues[1] = (key); }
                        }
                    }

                    if (compareValues[0] > compareValues[1]) {
                        System.out.println("Player 1: " + hand1 + " (" + Hand.nameMap.get(hand1.getHandValue()) +
                                ")\nPlayer 2: " + hand2 +  "  (" + Hand.nameMap.get(hand2.getHandValue()) +
                                ")\n\t\t\t    Player 1 wins.\n");
                        p1wins++;
                    }
                    else {
                        System.out.println("Player 1: " + hand1 + "  (" + Hand.nameMap.get(hand1.getHandValue()) +
                                ")\nPlayer 2: " + hand2 + "  (" + Hand.nameMap.get(hand2.getHandValue()) +
                                ")\n\t\t\t    Player 2 wins.\n");
                        p2wins++;
                    }
                }

            }
            System.out.println("Number of hands won by player 1: " + p1wins);
            System.out.println("Number of hands won by player 2: " + p2wins);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}