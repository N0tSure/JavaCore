package com.artemsirosh.tij.strings;

/**
 * Created by cresh on 11.02.16.
 */
public class Receipt {
    private double total;
    private static final int itemNameLength = 15;
    private static final int quantityValueLength = 5;
    private static final int priceValueLength = 10;

    private String withoutFloat() {
        return String.format("%4$c-%1$ds %4$c%2$ds %4$c%3$ds\n",
                itemNameLength,quantityValueLength,priceValueLength,'%');
    }

    private String withFloat() {
        return String.format("%4$c-%1$ds %4$c%2$ds %4$c%3$d.2f\n",
                itemNameLength,quantityValueLength,priceValueLength,'%');
    }

    private String withToFloat() {
        return String.format("%4$c-%1$d.%1$ds %4$c%2$ds %4$c%3$d.2f\n",
                itemNameLength,quantityValueLength,priceValueLength,'%');
    }

    private void printTitle() {
        System.out.printf(this.withoutFloat(), "Item", "Qty", "Price");
        System.out.printf(this.withoutFloat(), "----", "---", "-----");
    }

    private void print(String name,int qty,double price) {
        System.out.printf(this.withToFloat(), name, qty, price);
        total+=price;
    }

    private void printTotal() {
        System.out.printf(this.withFloat(), "Tax", "", total*0.06);
        System.out.printf(this.withoutFloat(), "", "", "-----");
        System.out.printf(this.withFloat(), "Total", "", total*1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Хуйцы",3,34);
        receipt.print("Греча",1,28);
        receipt.print("Непонятный борщ",1,36);
        receipt.printTotal();
    }
}
