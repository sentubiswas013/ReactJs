import java.util.*;

class Book {
    String id;
    String title;
    boolean issued;

    Book(String id, String title) {
        this.id = id;
        this.title = title;
    }
}

class Library {

    Map<String, Book> books = new HashMap<>();

    void add(Book book) {
        books.put(book.id, book);
    }

    boolean issue(String id) {
        Book book = books.get(id);
        if (book != null && !book.issued) {
            book.issued = true;
            return true;
        }
        return false;
    }

    void list() {
        books.values().forEach(
            b -> System.out.println(b.id + " : " + b.title + " : " + b.issued)
        );
    }
}

public class Main {
    public static void main(String[] args) {

        Library lib = new Library();
        lib.add(new Book("1", "Java Basics"));
        lib.add(new Book("2", "DSA"));

        lib.issue("1");
        lib.list();
    }
}