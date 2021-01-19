package com.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void addBook() {
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        Library library = new Library();
        assertEquals("The Renaisance", library.addBook(book1, 7));
    }

    @Test
    void makeRequest() {
        Library library = new Library();
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        assertTrue(library.makeRequest(emmanuel));

    }

    @Test
    void borrowBook() {
        Library library = new Library();
        Borrower oma = new Borrower("Oma", "Salifu", "junior student");
        Borrower ifeanyi = new Borrower("Ifeanyi", "Ibe", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower david = new Borrower("David", "Odohi", "Teacher");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook(book1, 3);
        library.makeRequest(oma);
        library.makeRequest(ifeanyi);
        library.makeRequest(david);
        library.makeRequest(emmanuel);
        assertEquals(david, library.borrowBook(book1));

    }

    @Test
    void returnBook() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Book book1 = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook(book1, 3);
        library.makeRequest(okoro);
        library.makeRequest(ome);
        library.makeRequest(jeniffer);
        library.borrowBook(book1);
        assertTrue(library.returnBook(okoro, book1));
    }

    @Test
    void totalBooksTaken() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Book book = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook(book, 3);
        library.makeRequest(jeniffer);
        library.makeRequest(ome);
        library.makeRequest(emmanuel);
        library.makeRequest(jeniffer);
        library.borrowBook(book);
        library.borrowBook(book);
        assertEquals(2, library.totalBooksTaken(book));
    }

    @Test
    void addToQueue() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Book book = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook(book, 3);
        assertTrue(library.addToQueue(jeniffer));
    }

    @Test
    void selectFromQueue() {
        Library library = new Library();
        Borrower jeniffer = new Borrower("Jeniffer", "Ezeobi", "senior student");
        Borrower ome = new Borrower("Omenebele", "Ananenu", "junior Student");
        Borrower okoro = new Borrower("Okoro", "Abbey", "Senior Student");
        Borrower emmanuel = new Borrower("Emmanuel", "Ogheneovo", "teacher");
        Book book = new Book("The Renaisance", "James Wallace", "1985");
        library.addBook(book, 3);
        library.makeRequest(jeniffer);
        library.makeRequest(ome);
        library.makeRequest(emmanuel);
        library.makeRequest(jeniffer);
        assertEquals(emmanuel, library.borrowBook(book));
    }
}