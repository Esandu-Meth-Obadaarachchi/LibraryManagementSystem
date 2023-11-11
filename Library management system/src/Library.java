public class Library {
    private  Object arr[];
    private Book[] books;
    private  int numOfBooks;
    private  int capacity;

    public Library(int capacity){
        this.capacity=capacity;
        numOfBooks=0;
        arr = new Object[capacity];
    }
    public void add(Book book){
        arr[numOfBooks++] = book;
    }
    public int remove(String bookToRemoveISBN) {
        int indexToRemove = -1;

        // Find the index of the book to remove
        for (int i = 0; i < numOfBooks; i++) {
            if (arr[i] instanceof Book) {
                Book book = (Book) arr[i];
                if (book.getISBN().equals(bookToRemoveISBN)) {
                    indexToRemove = i;
                    break; // Exit the loop as soon as the book is found
                }
            }
        }

        if (indexToRemove != -1) {
            // Shift elements to the left to remove the book
            for (int i = indexToRemove; i < numOfBooks - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[numOfBooks - 1] = null; // Clear the last element
            numOfBooks--;
        }
        return indexToRemove;
    }

    public Book get(int index){
        return (Book) arr[index];
    }
    public int getNumOfBooks(){
        return numOfBooks;
    }
    // Add a method to get all books
    public Book[] getBooks() {
        Book[] allBooks = new Book[numOfBooks];
        System.arraycopy(books, 0, allBooks, 0, numOfBooks);
        return allBooks;
    }

}
