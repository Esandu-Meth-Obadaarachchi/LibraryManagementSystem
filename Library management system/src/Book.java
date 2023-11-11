import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String ISBN;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(String title,String ISBN,String author,String genre,boolean isAvailable){
        this.title=title;
        this.ISBN=ISBN;
        this.author=author;
        this.genre=genre;
        this.isAvailable=isAvailable;
    }

    public Book(String title,String ISBN){
        this.title=title;
        this.ISBN=ISBN;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String  ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
    public String printBook(){
        return "title: "+title+", availability: "+isAvailable;
    }
}
