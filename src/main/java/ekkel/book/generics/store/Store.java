package ekkel.book.generics.store;

import java.util.ArrayList;

/**
 * Created by cresh on 29.08.16.
 */
class Store extends ArrayList<Aisle> {
    private ArrayList<CheckoutStand> checkoutStands = new ArrayList<>();
    private Office office = new Office();
    public Store(int amountAisle, int amountShelves, int amountProduct) {
        for (int i = 0; i < amountAisle; i++) {
            this.add(new Aisle(amountShelves,amountProduct));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Aisle aisle : this) {
            for (Shelf shelf : aisle)
                for (Product product : shelf) {
                    result.append(product);
                    result.append("\n");
                }
        }
        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(new Store(14,5,10));
    }
}
