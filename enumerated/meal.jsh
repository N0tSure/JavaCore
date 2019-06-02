import com.artemsirosh.tij.enumerated.menu.Course;

import java.util.Arrays;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

    Stream.iterate(Course.class, UnaryOperator.identity()).map(Class::getEnumConstants).limit(5).peek(courses -> System.out.println("----------")).flatMap(Arrays::stream).map(Course::randomSelection).forEach(System.out::println);