package UOPeople.week2;
// getting 'Scanner' class to use it (note that 'Textio' doesn't work)

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // making an object of 'Scanner' class calling it 'input'
        Scanner input = new Scanner(System.in);
        Book book1 = new Book("Harry Potter", "Joan Rowling", 10);
        Book book2 = new Book("Think Python", "Allen Downey", 7);
        Book book3 = new Book("Introduction to Programming Using Java", "David J. Eck", 10);
        ArrayList<Book> libBook = new ArrayList<>();
        libBook.add(book1);
        libBook.add(book2);
        libBook.add(book3);

        String[] process = {"Add Books", "Borrow Books", "Return Books", "Books we have", "Exit"};
        int processNum = 0;
        while (true) {
            for (int i = 0; i < 5; i++){
                System.out.println( (i+1) + " - " + process[i]);
            }
            System.out.print("Chose the process you want by number (1 - 5): ");
            try {
                processNum = input.nextInt();
                if (0 >= processNum || processNum > 5) {
                    throw new java.util.InputMismatchException();
                }
            }catch (java.util.InputMismatchException e){
                System.out.println("Chose the process you want by number (1 - 5): ");
                input.next();
            }

            String book_name;
            String book_auther;
            int book_quantity;
            switch (processNum){
                case 1 -> {
                    book_name = bookName();
                    book_auther = bookAuther();
                    book_quantity = bookQuantity();
                    addBook(book_name, book_auther, book_quantity, libBook);
                    System.out.println("Successful process");
                }case 2 -> {
                    book_name = bookName();
                    book_auther = bookAuther();
                    book_quantity = bookQuantity();
                    borrowBook(book_name, book_auther, book_quantity, libBook);
                }case 3 -> {
                    book_name = bookName();
                    book_auther = bookAuther();
                    book_quantity = bookQuantity();
                    returnBook(book_name, book_auther, book_quantity, libBook);
                }case 4 ->{
                    printBooks(libBook);
                }case 5 -> {
                    System.exit(0);
                }
            }
        }
    }

    public static String bookName(){
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the book name");
                String book_name = input.nextLine();
                if (checkIfIsAlpha(book_name)) {
                    return book_name;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("You can't enter a number as a name");
                input.nextLine();
            }
        }
    }

    public static String bookAuther(){
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the auther name: ");
                String auther_name = input.nextLine();
                if (!checkIfIsAlpha(auther_name)) {
                    throw new IllegalArgumentException();
                }
                return auther_name;
            }catch(IllegalArgumentException e) {
                System.out.println("You can't enter a number as a name");
                input.nextLine();
            }
        }
    }

    public static int bookQuantity(){
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the book quantity");
                int book_quantity = input.nextInt();
                if (book_quantity > 0) {
                    return book_quantity;
                }
                throw new IllegalArgumentException();
            }catch (java.util.InputMismatchException j){
                System.out.println("Enter a number");
                input.next();
            }catch (IllegalArgumentException e){
                System.out.println("You can't enter a number less than 1");
                input.next();
            }
        }
    }

    public static int addBook(String book_name, String book_auther, int book_quantity, ArrayList<Book> libBook){
        for(int i = 0; i < libBook.size(); i++) {
            if (libBook.get(i).name.equalsIgnoreCase(book_name)){
                libBook.get(i).quantity = book_quantity;
                return 0;
            }
        }
        libBook.add(new Book(book_name, book_auther, book_quantity));
        return 2;
    }

    public static void borrowBook(String book_name, String book_auther, int book_quantity, ArrayList<Book> libBook){
        for (int i = 0; i < libBook.size(); i++){
            if(libBook.get(i).name.equalsIgnoreCase(book_name)) {
                if(libBook.get(i).autherName.equalsIgnoreCase(book_auther)) {
                    if (libBook.get(i).quantity >= book_quantity) {
                        libBook.get(i).quantity = libBook.get(i).quantity - book_quantity;
                        System.out.println("Here is your books");
                    }else {
                        System.out.println("The existing quantity of the book isn't enough.");
                    }
                }else {
                    System.out.println("There is no book had made by this auther in our library");
                }
            }
        }
    }

    public static int returnBook(String book_name, String book_auther, int book_quantity,ArrayList<Book> libBook){
         for(int i = 0; i < libBook.size(); i++) {
             if (libBook.get(i).name.equalsIgnoreCase(book_name)) {
                 if(libBook.get(i).autherName.equalsIgnoreCase(book_auther)) {
                     if (libBook.get(i).quantity >= book_quantity) {
                         libBook.get(i).quantity = libBook.get(i).quantity + book_quantity;
                         System.out.println("Thank you for returning the book(s)");
                         return 0;
                     }
                 }
             }
         }
        System.out.println("The Book " + book_name + " not found");
         return 1;
    }

    public static boolean checkIfIsAlpha(String word){
        for(int i = 0; i < word.length(); i++){
            if(! (word.substring(i).compareToIgnoreCase("A") >= 0 ||
                    word.substring(i).compareToIgnoreCase("Z") <=0 )){
                return false;
            }
        }
        return true;
    }

    public static void printBooks(ArrayList<Book> libBook){
        System.out.println("Books we have: ");
        for(int i = 0; i < libBook.size(); i++){
            System.out.print((i+1) + " - " + libBook.get(i).name);
            System.out.print( " the auther name is " + libBook.get(i).autherName);
            System.out.print( " the quantity we have is " + libBook.get(i).quantity);
            System.out.println("\n");
        }
    }
}
