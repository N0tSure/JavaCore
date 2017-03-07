package ekkel.book.generics.army;

import java.util.ArrayList;

/**
 * Created by cresh on 29.08.16.
 */
class ArmyOfDistraction extends ArrayList<Division> {
    ArmyOfDistraction(int soldiers, int groups, int divisions) {
        for (int i = 0; i < divisions; i++) {
            this.add(new Division(soldiers,groups));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Division division : this)
            for (Group group : division)
                for (Unit unit : group) {
                    result.append(unit);
                    result.append("\n");
                }

        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(new ArmyOfDistraction(10,5,15));
    }
}
