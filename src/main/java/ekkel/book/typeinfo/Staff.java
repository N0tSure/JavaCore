package ekkel.book.typeinfo;

import ekkel.book.typeinfo.pets.Person;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cresh on 15.08.16.
 */
class Staff extends ArrayList<Position> {
    public Staff(String... titles) {
        this.add(titles);
    }

    public void add(String title, Person person) {
        this.add(new Position(title, person));
    }

    public void add(String... titles) {
        for (String title : titles) {
            add(new Position(title));
        }
    }

    public boolean positionAvailable(String title) {
        for (Position position : this)
            if (position.getTitle().equals(title) && position.getPerson().equals(Person.NULL))
                return true;
        return false;
    }

    public void fillPosition(String title, Person hire) {
        for (Position position : this)
            if (position.getTitle().equals(title) && position.getPerson().equals(Person.NULL)) {
                position.setPerson(hire);
                return;
            }
        throw new RuntimeException(String.format("Position %s not available",title));
    }

    public static void main(String[] args) {
        Staff staff = new Staff("President","CTO","Marketing Manager", "Product Manager", "Project Lead",
                "Software Engineer", "Software Engineer", "Software Engineer", "Test Engineer",
                "Technical Writer");
        staff.fillPosition("President",new Person("Me","Last","The Top, Lonely At"));
        staff.fillPosition("Project Lead",new Person("Janet","Planner","The Burbs"));
        if (staff.positionAvailable("Software Engineer"))
            staff.fillPosition("Software Engineer",new Person("Artem","Sirosh","SPb"));
        for (Position position : staff) {
            System.out.println(position);
        }
    }
}
