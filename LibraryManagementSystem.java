import java.util.*;

// LibraryOperations Interface
interface LibraryOperations {
    void addBook(Book book);
    void issueBook(String title, String issuedTo);
    void returnBook(String title);
    void displayBooks();
}

// Book Class
class Book {
    String title;
    String author;
    String issuedTo;
    boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }
}

// Library Class
class Library implements LibraryOperations {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.title);
    }

    @Override
    public void issueBook(String title, String issuedTo) {
        for (Book book : books) {
            if (book.title.equals(title) && !book.isIssued) {
                book.isIssued = true;
                book.issuedTo = issuedTo;
                System.out.println("Book issued: " + title + " to " + issuedTo);
                return;
            }
        }
        System.out.println("Book not available: " + title);
    }

    @Override
    public void returnBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title) && book.isIssued) {
                book.isIssued = false;
                book.issuedTo = null;
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("Book not found: " + title);
    }

    @Override
    public void displayBooks() {
        for (Book book : books) {
            String status = book.isIssued ? "Issued to " + book.issuedTo : "Available";
            System.out.println("Title: " + book.title + ", Author: " + book.author + ", Status: " + status);
        }
    }
}

// Main Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    Book book = new Book(title, author);
                    library.addBook(book);
                    break;

                case 2:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = scanner.nextLine();
                    System.out.print("Enter name of person to issue to: ");
                    String issuedTo = scanner.nextLine();
                    library.issueBook(issueTitle, issuedTo);
                    break;

                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}