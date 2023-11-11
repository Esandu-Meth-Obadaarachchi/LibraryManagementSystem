import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
public class Main {
    static String name;
    static String title;
    static String ISBN;
    static String author;
    static String genre;
    static boolean isAvailable;
    static String memberId;
    static String memId;
    static String nameMem;
    static ArrayList bB;
    static String staffId;
    static String staffName;

    static String role;
    private static ArrayList<LibraryMember> members = new ArrayList<>();
    private static ArrayList<LibraryStaff> staffMembers = new ArrayList<>();
    private static ArrayList<String> allBooks = new ArrayList<>();

    static Library lib1 = new Library(100);


    //===========================================================
    public static void RFF(){

        File file = new File("library.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                title = line.split("/")[0];
                ISBN = line.split("/")[1];
                author = line.split("/")[2];
                genre = line.split("/")[3];
                isAvailable = Boolean.parseBoolean(line.split("/")[4]);

                lib1.add(new Book(title, ISBN, author, genre, isAvailable));
            }
        } catch (FileNotFoundException e) {
            System.out.println("DATE FILE NOT FOUND!!! DO SOMETHING NIGGA =) ");
        } catch (IOException e) {
            System.out.println("there is a error in the text file data");
        }
    }
    //===========================================================

 //===========================================================
    public static void STF(){
        try {
            FileWriter fw = new FileWriter("library.txt", false); // false to overwrite the file

            for (int i = 0; i < lib1.getNumOfBooks(); i++) {
                Book book = lib1.get(i);
                fw.write(book.getTitle() + "/" + book.getISBN() + "/" + book.getAuthor() + "/" + book.getGenre() + "/" + book.isAvailable() + "\n");
            }

            fw.close(); // Remember to close the FileWriter when you're done
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //=======================================================
    public static void STFMembers(){// writing the members
        try (FileOutputStream fos = new FileOutputStream("members.ser",false);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (LibraryMember member : members) {
                memId = member.getMemberId();
                nameMem = member.getName();
                bB = member.getBorrowedBooks();

                oos.writeObject(memId);
                oos.writeObject(nameMem);
                oos.writeObject(bB);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void RFFMembers(){// load
        File file = new File("members.ser");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    memId = (String) ois.readObject();
                    nameMem = (String) ois.readObject();
                    bB = (ArrayList<Book>) ois.readObject();

                    LibraryMember newGuy = new LibraryMember(memId, nameMem, bB);
                    members.add(newGuy);
                    System.out.println(newGuy);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
            System.out.println("========");
        } catch (FileNotFoundException e) {
            System.out.println("DATA FILE NOT FOUND!!! DO SOMETHING NIGGA =) ");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There is an error in the data file.");
        }
    }
    //===========================================================
    public static void RFFStaff() {
        File file = new File("staff.ser");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    LibraryStaff newGuy2 = (LibraryStaff) ois.readObject();
                    staffMembers.add(newGuy2);
                    System.out.println(newGuy2);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
            System.out.println("========");
        } catch (FileNotFoundException e) {
            System.out.println("DATA FILE NOT FOUND!!! DO SOMETHING  =) ");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There is an error in the data file.");
        }
    }


    public static void STFStaff() {
        try (FileOutputStream fos = new FileOutputStream("staff.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (LibraryStaff staffP : staffMembers) {
                oos.writeObject(staffP); // Write the entire LibraryStaff object
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Logger logger = Logger.getLogger("LibrarySystem");

    //=================================================
    public static void main(String[] args) {



        try {
            // Create a FileHandler to write logs to a file named "library.log"
            FileHandler fileHandler = new FileHandler("library.log",true);

            // Create a SimpleFormatter to format log messages
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Add the FileHandler to the logger
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Application started.");

        RFFMembers();
        RFFStaff();
        RFF();



        System.out.println("================================");
        System.out.println("PRESS 1 , IF YOU ARE A STAFF MEMBER");
        System.out.println("PRESS 2 , if you are a Member");
        System.out.println("PRESS 3, TO EXIT THE SYSTEM");
        System.out.println("================================");

        int options = 0;

        while (options != 3) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Enter the option number: ");
                    options = sc.nextInt();
                    break; // Exit the loop when valid input is provided
                } catch (InputMismatchException e) {
                    System.out.println("Invalid type of input");
                    sc.nextLine(); // Consume the invalid input
                }
            }

            switch (options) {
                //==========================================================
                case 1:
                    System.out.println("================================");
                    System.out.println("Press 1 to Add a new book");
                    System.out.println("Press 2 to Remove a new Book");
                    System.out.println("Press 3 to add a new Member");
                    System.out.println("Press 4 to view the Members and the details");
                    System.out.println("Press 5 to borrow out a book");
                    System.out.println("Press 6 to return a book");
                    System.out.println("Press 7, to add a new staff Member");
                    System.out.println("PRESS 10, TO EXIT THE SYSTEM");
                    System.out.println("================================");

                    int staffOptions = 0;

                    while (true) {
                        try {
                            System.out.print("Enter the STAFF option number: ");
                            staffOptions = sc.nextInt();

                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("invalid type of input");
                            sc.nextLine();
                        }
                    }

                    switch (staffOptions) {
                        //---------------------------------------------------------
                        case 1:
                            Scanner scanner = new Scanner(System.in);

                            System.out.print("Title: ");
                            title = scanner.nextLine();

                            System.out.print("ISBN number: ");
                            ISBN = scanner.nextLine();

                            System.out.print("Author: ");
                            author = scanner.nextLine();

                            System.out.print("Genre: ");
                            genre = scanner.nextLine();

                            System.out.print("Is the book available to borrow (T/F): ");
                            String availableAnswer = sc.next();

                            if (availableAnswer.equalsIgnoreCase("t")) {
                                isAvailable = true;
                            } else {
                                isAvailable = false;
                            }

                            System.out.println("\nBook Information");
                            System.out.println("Title: " + title);
                            System.out.println("ISBN: " + ISBN);
                            System.out.println("Author: " + author);
                            System.out.println("Genre: " + genre);


                            lib1.add(new Book(title, ISBN, author, genre, isAvailable));

                            logger.info("new book added!.");
                            System.out.println("new book added!");
                            STF();

                            break;
                        //--------------------------------------------------------
                        case 2:// deleting a book
                            String bookToDelete;
                            Scanner scanner3 = new Scanner(System.in);
                            while (true) {
                                try {
                                    System.out.print("Enter the ISBN number of the book to delete: ");
                                    bookToDelete = scanner3.next();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid data type entered!! TRY AGAIN");
                                    System.out.print("Enter the ISBN number of the book to delete: ");
                                    bookToDelete = scanner3.next();
                                }
                            }
                            int removedIndex = lib1.remove(bookToDelete);
                            if (removedIndex == -1) {
                                System.out.println("book not found");
                            } else {
                                System.out.println("book deleted");
                                STF();
                            }
                            logger.info("book deleted!.");
                            break;
                        //-----------------------------------------------------------
                        case 3://adding new members
                            Scanner scanner1 = new Scanner(System.in);

                            System.out.print("Enter the new memberID:");
                            memberId = scanner1.next();

                            System.out.print("Enter the name of the member: ");
                            name = scanner1.next();

                            LibraryMember mem = new LibraryMember(memberId, name);
                            members.add(mem);

                            System.out.println("new member added");
                            STFMembers();

                            logger.info("new member added.");
                            break;
                        //-----------------------------------------------------------
                        case 4://printing all the members and their borrowed books
                            System.out.println("members and their borrowed books: ");
                            for (int l = 0; l < members.size(); l++) {
                                System.out.println(members.get(l).toViewMember());

                            }
                            logger.info("members printed .");
                            break;

                        //--------------------------------------------------------------
                        case 5:// checking out a book and adding that book to the members array of borrowed
                            boolean found = false;
                            String ISBNBorrowed;
                            String borrowingMem;
                            System.out.print("Enter the ISBN: ");
                            ISBNBorrowed = sc.next();

                            System.out.print("Enter the MemberID: ");
                            borrowingMem = sc.next();

                            for (int k = 0; k < lib1.getNumOfBooks(); k++) {
                                if (ISBNBorrowed.equals(lib1.get(k).getISBN())) {
                                    if (lib1.get(k).isAvailable()){
                                        lib1.get(k).setAvailable(false);
                                        found = true;
                                        for (int l = 0; l < members.size(); l++) {
                                            if (members.get(l).getMemberId().equalsIgnoreCase(borrowingMem)) {
                                                members.get(l).addBorrowedBook(lib1.get(k));
                                                System.out.println("OK, book borrowed! ");
                                                logger.info("Book borrowed successfully.");
                                            }
                                        }
                                        break;
                                    }
                                    else {
                                        System.out.print("that book is already, Borrowed");
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("a book with that ISBN number was not found,try again");
                            }
                            STFMembers();
                            STF();
                            break;

                        case 6: // to return a book
                            Scanner scanner4 = new Scanner(System.in);
                            String ISBNToReturn;
                            String memberReturning;

                            System.out.print("Enter the book number to return: ");
                            ISBNToReturn = scanner4.next();

                            System.out.print("Enter the member ID of the member returning the book: ");
                            memberReturning = scanner4.next();

                            boolean bookFound = false;
                            int k = 0;
                            for ( k = 0; k < lib1.getNumOfBooks(); k++) {
                                if (ISBNToReturn.equals(lib1.get(k).getISBN())) {
                                    lib1.get(k).setAvailable(true);
                                    bookFound = true;
                                    System.out.println("Book successfully returned!");
                                    logger.info("Book returned successfully");
                                    break; // Exit the loop when the book is found
                                }
                            }

                            if (!bookFound) {
                                System.out.println("Book with ISBN " + ISBNToReturn + " not found.");
                            } else {
                                for (int l = 0; l < members.size(); l++) {
                                    if (members.get(l).getMemberId().equalsIgnoreCase(memberReturning)) {
                                        members.get(l).removeBorrowedBook(lib1.get(k)); // Pass ISBN to remove
                                        break; // Exit the loop when the member is found
                                    }
                                }
                            }

                            STFMembers();
                            STF();

                            break;

                        case 7://adding new staff
                            Scanner scanner2 = new Scanner(System.in);

                            System.out.print("Enter the new staffID:");
                            staffId = scanner2.next();

                            System.out.print("Enter the name of the staff member: ");
                            staffName = scanner2.next();

                            System.out.print("Enter the role given: ");
                            role = scanner2.next();
                            LibraryStaff newNigga = new LibraryStaff(staffId, staffName, role);
                            staffMembers.add(newNigga);
                            STFStaff();
                            System.out.println("staff member added!");
                            logger.info("new Staff member added.");
                            break;

                        case 10:
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + options);
                    }
                break;
                case 2:
                    System.out.println("To search books by title, press (1)");
                    System.out.println("To search books by authors, press (2)");
                    System.out.println("To search books by genre, press (3)");

                    Scanner scanner6 = new Scanner(System.in);
                    int memberOption;

                    while (true) {
                        try {
                            System.out.print("Enter the letter to proceed: ");
                            memberOption = scanner6.nextInt();
                            scanner6.nextLine(); // Consume the newline character
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid type of input");
                            scanner6.nextLine(); // Consume the invalid input
                        }
                    }

                    switch (memberOption) {
                        case 1:
                            System.out.print("Enter the title of the book: ");
                            String bookTitle = scanner6.nextLine();
                            System.out.println(bookTitle);
                            boolean found = false;

                            for (int h = 0; h < lib1.getNumOfBooks(); h++) {
                                if (lib1.get(h).getTitle().equalsIgnoreCase(bookTitle)) {
                                    System.out.println(lib1.get(h).printBook());;
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                System.out.println("Book not found.");
                            }
                            logger.info("books searched by title");
                            break;


                        case 2:
                            System.out.print("Enter the author of the book: ");
                            String bookAuthor = scanner6.nextLine();
                            found = false;


                            for (int h = 0; h < lib1.getNumOfBooks(); h++) {
                                if (lib1.get(h).getAuthor().equalsIgnoreCase(bookAuthor)) {
                                    System.out.println(lib1.get(h).printBook());
                                    found=true;
                                }
                            }

                            if (!found) {
                                System.out.println("Books not found.");
                            }
                            logger.info("books searched by author");
                            break;

                        case 3:
                            System.out.print("Enter the genre of the books: ");
                            String bookGenre = scanner6.nextLine();
                            found = false;


                            for (int h = 0; h < lib1.getNumOfBooks(); h++) {
                                if (lib1.get(h).getGenre().equalsIgnoreCase(bookGenre)) {
                                    System.out.println(lib1.get(h).printBook());
                                    found=true;
                                }
                            }

                            if (!found) {
                                System.out.println("Books not found.");
                            }
                            logger.info("books searched by genre");
                            break;
                    }
                    break;

            }

        }
        logger.info("PROGRAM ENDED!");
    }
}


