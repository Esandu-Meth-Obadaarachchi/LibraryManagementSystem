import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LibraryMember  implements Serializable {
    private String memberId;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public LibraryMember(String memberId,String name){ //only these 2 as when a new member is added , no books borrowed
        this.memberId=memberId;
        this.name=name;
        this.borrowedBooks = new ArrayList<Book>();
    }
    public LibraryMember(String memberId,String name,ArrayList<Book> borrowedBooks){
        this.memberId=memberId;
        this.name=name;
        this.borrowedBooks = borrowedBooks;
    }
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void addBorrowedBook(Book book){
        this.borrowedBooks.add(book);
    }
    public void removeBorrowedBook(Book bookies) {
        String isbn = bookies.getISBN();
        // Iterate through the borrowedBooks list and remove the book with the given ISBN
        Iterator<Book> iterator = borrowedBooks.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getISBN().equals(isbn)) {
                iterator.remove(); // Remove the book with the matching ISBN
                break; // Exit the loop when the book is found and removed
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
    public String toViewMember(){
        return "LibraryMember{" + "memberId='" + memberId+", borrowedBooks=" + borrowedBooks+'}';
    }
}
