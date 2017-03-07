package ekkel.book.arrays;

class Digit implements Comparable<Digit> {
    private int digit;

    public Digit(int digit) {
        this.digit = digit;
    }

    @Override
    public int compareTo( Digit o) {
        return Integer.compare(this.digit, o.digit);
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Digit)) return false;
        Digit d = (Digit) obj;
        return this.digit == d.digit;
    }
}
