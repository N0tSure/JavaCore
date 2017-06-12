package test02;

/**
 * Interface of character searcher, use for better testing
 *
 */
public interface CharacterCounter {

    /**
     * Counts entries of given character
     * @param keyChar character that will counted
     * @param target directory of file, which contains searched characters
     * @return number of founded chars
     * @throws Exception if something going wrong
     */
    int countCharacters(char keyChar, String target) throws Exception;

}
