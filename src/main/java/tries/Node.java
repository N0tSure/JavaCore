package tries;

/**
 * <p>
 * Created on 12.07.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
class Node {
    private static final int KEYS_AMOUNT = 27;

    private boolean isLeaf;
    private Node[] children;

    public Node(boolean isLeaf) {
        this.children = new Node[KEYS_AMOUNT];
        this.isLeaf = isLeaf;
    }

    void addChild(char[] word, int pos, int length) {
        char c = word[pos++];
        int index = getIndex(c);

        if (children[index] == null)
            children[index] = new Node(false);

        if (pos < length) {
            children[index].addChild(word, pos, length);
        } else {
            children[index].isLeaf = true;
        }

    }

    boolean isPresent(char[] word, int pos, int length) {
        char c = word[pos++];
        int index = getIndex(c);

        if (children[index] != null) {

            if (pos < length) {
                return children[index].isPresent(word, pos, length);
            } else {
                return children[index].isLeaf;
            }
        }

        return false;
    }

    void clear() {

        if (!isLeaf) {
            for (int i = 0; i < KEYS_AMOUNT; i++) {
                children[i].clear();
                children[i] = null;
            }
        }
    }

    private int getIndex(char c) {
        int result;
        if (c != '\'')
            result = c - 65;
        else
            result = 26;

        return result;
    }
}
