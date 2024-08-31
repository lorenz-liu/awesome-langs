import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.*;

// Generic class
public class JavaShowcase<T> implements Runnable {
    private T value;

    // Constructor
    public JavaShowcase(T value) {
        this.value = value;
    }

    // Generic method
    public <U> void printBoth(U another) {
        System.out.println(value + ", " + another);
    }

    // Enum
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    // Inner class
    private class InnerClass {
        void display() {
            System.out.println("Inner class");
        }
    }

    // Static nested class
    static class StaticNestedClass {
        static void display() {
            System.out.println("Static nested class");
        }
    }

    // Interface
    interface Printable {
        void print();
    }

    // Lambda expression
    Printable lambdaPrintable = () -> System.out.println("Printed from lambda");

    // Method reference
    Printable methodReferencePrintable = System.out::println;

    // Functional interface and lambda
    Supplier<String> supplier = () -> "Hello from supplier";

    // Stream API
    public void streamExample() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                         .filter(n -> n % 2 == 0)
                         .map(n -> n * n)
                         .reduce(0, Integer::sum);
        System.out.println("Sum of squares of even numbers: " + sum);
    }

    // Exception handling
    public void exceptionExample() {
        try {
            throw new Exception("Custom exception");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed");
        }
    }

    // Try-with-resources
    public void tryWithResourcesExample() {
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Varargs
    public static void printAll(String... strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }

    // Annotations
    @Override
    @SuppressWarnings("unchecked")
    public void run() {
        System.out.println("Running...");
    }

    // Multithreading
    public void threadExample() {
        Thread t = new Thread(() -> {
            System.out.println("Running in a separate thread");
        });
        t.start();
    }

    // Concurrent API
    public void concurrentExample() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(() -> "Task result");
        System.out.println(future.get());
        executor.shutdown();
    }

    // NIO Files
    public void fileExample() throws IOException {
        Path path = Paths.get("test.txt");
        Files.write(path, Arrays.asList("Line 1", "Line 2"), StandardOpenOption.CREATE);
    }

    // Optional
    public void optionalExample() {
        Optional<String> optional = Optional.of("value");
        optional.ifPresent(System.out::println);
    }

    // Switch expression (Java 14+)
    public String switchExample(Day day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
        };
    }

    // Record (Java 16+)
    record Person(String name, int age) {}

    // Sealed classes (Java 17+)
    sealed interface Shape permits Circle, Rectangle {}
    final class Circle implements Shape {}
    final class Rectangle implements Shape {}

    public static void main(String[] args) throws Exception {
        JavaShowcase<String> showcase = new JavaShowcase<>("Hello");
        showcase.printBoth(42);
        showcase.streamExample();
        showcase.exceptionExample();
        showcase.threadExample();
        showcase.concurrentExample();
        showcase.fileExample();
        showcase.optionalExample();
        printAll("One", "Two", "Three");

        // Using record
        Person person = new Person("John", 30);
        System.out.println(person);

        // Pattern matching for instanceof (Java 16+)
        Object obj = "Hello";
        if (obj instanceof String s) {
            System.out.println(s.toUpperCase());
        }

        // Text blocks (Java 15+)
        String textBlock = """
            This is a
            multi-line
            text block
            """;
        System.out.println(textBlock);
    }
}
