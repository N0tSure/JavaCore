enum Shrubbery {
    GROUND, CRAWLING, HANGING
}

for (Shrubbery shrubbery : Shrubbery.values()) {
    System.out.println(shrubbery + " ordinal: " + shrubbery.ordinal());
    System.out.print(shrubbery.compareTo(Shrubbery.CRAWLING) + " ");
    System.out.print(shrubbery.equals(Shrubbery.CRAWLING) + " ");
    System.out.println(shrubbery == Shrubbery.CRAWLING);
    System.out.println(shrubbery.getDeclaringClass());
    System.out.println(shrubbery.name());
    System.out.println("---------------------------");
}
