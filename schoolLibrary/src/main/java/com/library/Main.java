package com.library;

public class Main {

    public static void main(String[] args) {
        Borrower oma = new Borrower("Oma", "Salifu", "junior student");
        Borrower david = new Borrower("David", "Odohi", "Teacher");
        Borrower ifeanyi = new Borrower("Ifeanyi", "Ibe", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Book book = new Book("Avatar", "James Cypher", "1997");
        Book book1 = new Book("Things fall apart", "Chinua Achebe", "1985");
        Book book2 = new Book("Riversdale", "Mike Thomase", "1997");
        Book book3 = new Book("The chronicles", "Karl Eindhovewt", "1948");
        Library library = new Library();
        library.addBook(book, 5);
        library.addBook(book, 7);
        library.addBook(book1, 3);
        library.addBook(book2, 3);
        library.addBook(book3, 7);
        library.totalBooksTaken(book);
        library.makeRequest(oma);
        library.makeRequest(okoro);
        library.makeRequest(ifeanyi);
        library.makeRequest(jeniffer);
        library.makeRequest(emmanuel);
        library.makeRequest(david);
        library.makeRequest(ome);
        Borrower val = library.borrowBook(book);
        System.out.println(val);
        library.makeRequest(emmanuel);
        Borrower val2 = library.borrowBook(book);
        library.makeRequest(emmanuel);
        System.out.println(val2);
        Borrower val3 = library.borrowBook(book);
        System.out.println(val3);
        library.addToQueue(ome);
        library.addToQueue(david);
        library.addToQueue(okoro);
        Borrower og = library.selectFromQueue(book2);
        Borrower on = library.selectFromQueue(book2);
        Borrower op = library.selectFromQueue(book3);
        library.makeRequest(emmanuel);
        library.returnBook(emmanuel, book);
        Borrower val4 = library.borrowBook(book);
        library.makeRequest(emmanuel);
        Borrower val5 = library.borrowBook(book1);
        library.totalBooksTaken(book);
        library.makeRequest(emmanuel);
        Borrower val6 = library.borrowBook(book);
        Borrower val7 = library.borrowBook(book);
        library.totalBooksTaken(book);

    }
}
