package com.artemsirosh.tij.collections.grep;


import java.util.Comparator;

/**
 * Created by cresh on 11.03.16.
 */
public class WordHolder implements Comparator<WordHolder>, Comparable<WordHolder> {
    private String word;
    private int counter;

    public WordHolder(String word) {
        this.word=word;
        counter=1;
    }

    public void match() {
        this.counter+=1;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(WordHolder o) {
        return this.word.compareTo(o.getWord());
    }

    @Override
    public int compare(WordHolder o1, WordHolder o2) {
        int n1=o1.getWord().length(), n2=o2.getWord().length();
        for (int i1=0, i2=0; i1<n1 && i2<n2; i1++, i2++) {
            char c1 = o1.getWord().charAt(i1);
            char c2 = o2.getWord().charAt(i2);
            if (c1 != c2) {
                c1 = Character.toUpperCase(c1);
                c2 = Character.toUpperCase(c2);
                if (c1 != c2) {
                    c1 = Character.toLowerCase(c1);
                    c2 = Character.toLowerCase(c2);
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                }
            }
        }
        return n1 - n2;
    }

    @Override
    public String toString() {
        return String.format("%-12s : %d",this.word,this.counter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordHolder that = (WordHolder) o;

        return !(word != null ? !word.equals(that.word) : that.word != null);

    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
    }
}
