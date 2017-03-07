package ekkel.book.typeinfo.pets;


/**
 * Created by cresh on 09.08.16.
 */
public class Individual implements Comparable<Individual> {
    private static long counter = 0;
    private final long id = counter++;
    private String name;

    public Individual(String name) {
        this.name = name;
    }

    public Individual() {
        //no-op
    }

    @Override
    public String toString() {
        return String.format("%s%s",this.getClass().getSimpleName(),name==null ? "" : " " + name);
    }

    public long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Individual && id==((Individual) o).id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (name!=null) result = 37*result + name.hashCode();
        result = 37*result + (int) id;
        return result;
    }

    @Override
    public int compareTo(Individual individual) {
        String first = this.getClass().getSimpleName();
        String argumentName = individual.getClass().getSimpleName();
        int firstCompare = first.compareTo(argumentName);
        if (firstCompare!=0) return firstCompare;
        if (this.name!=null && individual.name!=null) {
            int secondCompare = this.name.compareTo(individual.name);
            if (secondCompare!=0) return secondCompare;
        }
        return (this.id > individual.id ? -1 : (this.id==individual.id ? 0 : 1));
    }
}
