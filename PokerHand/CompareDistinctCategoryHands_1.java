import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompareDistinctCategoryHands_1 {
    public static void main(String[] args) {
        try {
            for (String line : Files.readAllLines(Paths.get("/Volumes/DATA/Java/CIS2168/Hwk9/distinctHandPairs.txt"))) {
                Hand hand1 = new Hand(line.substring(0, 14));
                Hand hand2 = new Hand(line.substring(14, 29).trim());

                System.out.println("Player 1: " + hand1 + "  (" + Hand.nameMap.get(hand1.getHandValue()) +
                        ")\t\tRank: " + Arrays.toString(hand1.getCardRanks()) +
                        "\nPlayer 2: " + hand2 + "  (" + Hand.nameMap.get(hand2.getHandValue()) +
                        ")\t\tRank: " + Arrays.toString(hand2.getCardRanks()));
                System.out.println(hand1.compare(hand1, hand2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
