import java.util.LinkedList;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.addFirst(0);
        queue.addLast(2);
        System.out.println(queue);

        // Real-world
        PrinterQueue printer = new PrinterQueue();
        printer.addJob("Invoice.pdf");
        printer.addJob("Report.docx");
        printer.addJob("Photo.png");
        printer.printNext();
        printer.printNext();
        printer.showQueue();
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Printer queue — FIFO using LinkedList
// ─────────────────────────────────────────────────────────────
class PrinterQueue {
    private LinkedList<String> queue = new LinkedList<>();

    void addJob(String doc)  { queue.addLast(doc); System.out.println("Queued: " + doc); }
    void printNext()         { System.out.println("Printing: " + queue.pollFirst()); }
    void showQueue()         { System.out.println("Remaining: " + queue); }
}

// Output:
// [0, 1, 2]
// Queued: Invoice.pdf  Queued: Report.docx  Queued: Photo.png
// Printing: Invoice.pdf
// Printing: Report.docx
// Remaining: [Photo.png]
