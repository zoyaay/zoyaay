import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class tugaskelompok3 {
    public static String[][] word = {
            {"dettli", "let", "die", "lie", "tie", "lit", "lid", "til", "eld", "diet", "tide", "edit", "idle", "tile", "lied", "tilt", "lite", "dite", "tied", "title", "tilde", "tiled", "titled", "tilted"},
            {"secaen", "can", "see", "sea", "sec", "ace", "sac", "case", "seen", "ease", "scan", "cane", "sane", "acne", "aces", "cans", "cease", "scene", "acnes", "canes", "seance", "encase"},
            {"hkrneo", "her", "one", "nor", "ore", "roe", "hen", "hoe", "eon", "hero", "horn", "hone", "honk", "hern", "heron", "honor", "honker"}
    };
    public static List<Integer> scores = new ArrayList<Integer>();
    public static List<String> answers = new ArrayList<String>();
    public static boolean loop = true;

    public static void main (String[] args)
    {
        do {
            System.out.println("Coepoe Word Puzzle");
            System.out.println("===================");
            System.out.println("      ");
            //rules//
            System.out.print("Rules:");
            System.out.println("1. Create a word using given characters, min 3 characters & max 6 characters.");
            System.out.println("2. Each level, You have 10 chances to answer correctly.");
            System.out.println("3. To get through to next level, you have to score 70 points each level.");

            games(1);
        } while (loop);
    }

    public static void games(int level) {
        Scanner input = new Scanner(System.in);
        List<String> gamePuzzle = new ArrayList(Arrays.asList(word[level-1]));
        gamePuzzle.remove(0);
        String[] quest = word[level-1][0].split("");

        answers.clear();
        if(scores.size() < level) {
            scores.add(0);
        }

        System.out.println("Level "+level+"\n-----");
        System.out.println("\t\t"+quest[0]+"\t"+quest[1]+"\t"+quest[2]+"\t"+quest[3]+"\t"+quest[4]+"\t"+quest[5]);

        for(int i = 1; i<=10; i++) {
            System.out.print(i+ "> Your answer: ");
            String answer = input.nextLine();

            if(answers.contains(answer)){
                System.out.println("You had already type this word");
                i--;
                continue;
            }
            if(gamePuzzle.contains(answer)){
                answers.add(answer);
                scores.set(level-1, scores.get(level-1)+10);
                System.out.println("#Right. Your Score "+scores.get(level-1));
            }
        }

        System.out.println("\nYou had answered 10 times with "+answers.size()+" right answers\n");

        for (int j=0; j<gamePuzzle.size(); j++) {
            System.out.print(gamePuzzle.get(j)+"\t"+ (j%6==0 ? "\n" : ""));
        }

        System.out.println("\n");

        if(scores.get(level-1) >= 70 && level < word.length) {
            games(level+1);
        } else if(scores.get(level-1) >= 70 && level == word.length) {
            int totalScore = scores.stream().mapToInt(Integer::intValue).sum();

            System.out.println("You win !!");
            System.out.println("Overall Score : "+totalScore);

            System.out.println("Press ENTER to exit...");

            loop = false;
            input.nextLine();

        } else {
            System.out.println("You Lose !! Try Again...");

            System.out.print("\nDo you want to retry [y/n]? ");
            String retry = input.nextLine();
            if (!retry.equalsIgnoreCase("y")) {
                loop = false;
            }
            scores.clear();
        }
    }
}
