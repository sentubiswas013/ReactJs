import java.util.*;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.add(new Book("1", "Java Basics"));
        lib.add(new Book("2", "DSA"));

        lib.issue("1");
        lib.list();

        // Real-world: library with return and search
        System.out.println("\n--- Real-world Library ---");
        Library school = new Library();
        school.add(new Book("B001", "Clean Code"));
        school.add(new Book("B002", "Design Patterns"));
        school.add(new Book("B003", "Effective Java"));

        System.out.println("Issue B001: " + school.issue("B001"));
        System.out.println("Issue B001 again: " + school.issue("B001")); // already issued
        System.out.println("Return B001: " + school.returnBook("B001"));
        System.out.println("Issue B001 after return: " + school.issue("B001"));

        school.list();
    }
}

class Book {
    String id, title;
    boolean issued;

    Book(String id, String title) { this.id = id; this.title = title; }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Library with issue, return, and listing
// ─────────────────────────────────────────────────────────────
class Library {
    Map<String, Book> books = new HashMap<>();

    void add(Book book) { books.put(book.id, book); }

    boolean issue(String id) {
        Book book = books.get(id);
        if (book != null && !book.issued) { book.issued = true; return true; }
        return false;
    }

    boolean returnBook(String id) {
        Book book = books.get(id);
        if (book != null && book.issued) { book.issued = false; return true; }
        return false;
    }

    void list() {
        books.values().forEach(b ->
            System.out.println(b.id + " | " + b.title + " | " + (b.issued ? "Issued" : "Available"))
        );
    }
}

// Output:
// 1 | Java Basics | Issued
// 2 | DSA | Available
// --- Real-world Library ---
// Issue B001: true
// Issue B001 again: false
// Return B001: true
// Issue B001 after return: true
// B001 | Clean Code | Issued
// B002 | Design Patterns | Available
// B003 | Effective Java | Available
