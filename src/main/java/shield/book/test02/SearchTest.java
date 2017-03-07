package shield.book.test02;

import org.junit.Test;

/* This test was wrote for compare realization
 * of CharacterSearcher and measure execution time */
public class SearchTest {

    /* There 2 files for test:
    * sample.txt - small sample file
    * data.txt - large sample file*/
    @Test
    public void countingTimeMeasureTest() throws Exception {
        // input samples
        char[] soughtCharacters = "onvMus는절에수ступТвон".toCharArray();
        String targetPath = "data.txt";

        CharacterSearcher obviousSearcher = new ObviousSearcher();
        CharacterSearcher quick = new QuickSearcher();
        runModule(obviousSearcher, soughtCharacters, targetPath, 5);
        runModule(quick, soughtCharacters, targetPath, 5);
    }

    /*Measure exec time and print results
    * @searcher CharacterSearcher that examine
    * @path path to sample file
    * @n number of attempts*/
    private void runModule(CharacterSearcher searcher, char[] keys, String path, int n) throws Exception {
        System.out.printf("Measurement of execution time for %s\n", searcher.getClass().getSimpleName());

        long before = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (char key : keys) {
                int counted = searcher.countCharacters(key, path);
                System.out.printf("For \'%c\': %d entries\n", key, counted);
            }
        }
        long after = System.currentTimeMillis();

        System.out.printf(
                "Total execution time for %d attempt of %d characters: %d\n",
                n, keys.length, after - before);
    }

}
