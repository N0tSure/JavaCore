package shield.book.lesson7.reflection;

public class Model {
    public int i;
    public String s;
    public static int statInt = 5;
    public static String statStr = "this is static string";
    private int hidedInteger;
    private String hidedString;
    private static String hidedStatStr = "this is hided static string";
    private static int hidedStatInteger = 15;

        Model() {
            hidedInteger = 10;
            hidedString = "This is hided string";
        }

    public int getHidedInteger() {
        return hidedInteger;
    }

    public void setHidedInteger(int hidedInteger) {
        this.hidedInteger = hidedInteger;
    }

    public String getHidedString() {
        return hidedString;
    }

    public void setHidedString(String hidedString) {
        this.hidedString = hidedString;
    }

    public static String getHidedStatStr() {
        return hidedStatStr;
    }

    public static void setHidedStatStr(String hidedStatStr) {
        Model.hidedStatStr = hidedStatStr;
    }

    public static int getHidedStatInteger() {
        return hidedStatInteger;
    }

    public static void setHidedStatInteger(int hidedStatInteger) {
        Model.hidedStatInteger = hidedStatInteger;
    }
}
