package test02;

/**
 * Main class of test.task 2
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length!=2) {
            System.out.println("Usage: program <searchingCharacter> <pathToFile>");
            System.exit(1);
        }
        char target = args[0].charAt(0);

        CharacterCounter searcher = new QuickCounter();
        System.out.printf("Results for %c: %d",target, searcher.countCharacters(target, args[1]));
    }
}
