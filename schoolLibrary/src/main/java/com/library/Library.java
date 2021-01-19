package com.library;

import java.util.*;

public class Library {
    private final HashMap<Book, Integer> availableCopies = new HashMap<>(); // keeps track of copies of a particular book available in library
    private final HashMap<Borrower, ArrayList<Book>> borrowersCollection = new HashMap<>(); // keeps track of collection of books by borrower
    private final PriorityQueue<Borrower> requester = new PriorityQueue<>(); //List of potential borrowers of books in order of priority
    private final HashMap<Book, Integer> noOfCopies = new HashMap<>(); // keeps track of total copies supplied to the library

    Queue<Borrower> queue = new LinkedList<>(); //List of potential borrowers of books without priority

    //Creating Library constructor
    public Library() {
        String name = "Magnifico";
        System.out.println("**********************************************************");
        System.out.println("Welcome to " + name + " library, ranked one of the best libraries in the world. In this library, we have" +
                " lots of books you can ever imagine.\nHop in and enjoy the ride");
        System.out.println("**********************************************************");
    }

    //add books to the list of collections in library
    public String addBook(Book book, int copies){
        //Increase total number of copies as well as the copies currently present in library
        noOfCopies.put(book, noOfCopies.getOrDefault(book, 0) + copies);
        availableCopies.put(book, availableCopies.getOrDefault(book, 0) + copies);
        System.out.println(copies + " books of " + book.getName() + " published by " + book.getAuthor() + " has been added to library book collections," +
                " available copies: " + availableCopies.get(book));
        return book.getName();
    }

    public boolean makeRequest(Borrower borrower){
        // Request is made to borrow book, check if eligible before adding to list
        return addToList(borrower, "makeRequest");
    }

    public Borrower borrowBook(Book book){
        //If there is no one in priority queue, no books will be given out
        return selectFromList(book, "borrowBook");
    }
    public boolean returnBook(Borrower borrower, Book book){

        /*1.Check if the person is among those who are yet to return borrowed books from the library
        * 2.Check if the book he is returning is among the books he borrowed
        * 3. Check if that particular collection of book is already complete in the library
        */
        if(borrowersCollection.containsKey(borrower) && borrowersCollection.get(borrower).contains(book)
        && noOfCopies.get(book) > availableCopies.get(book)){
            System.out.println("**********************************************************");
            System.out.println(borrowersCollection.get(borrower).size());
            borrowersCollection.get(borrower).remove(book);
            availableCopies.put(book, availableCopies.getOrDefault(book, 0) + 1);
            System.out.println(borrower.getFullName() + " has returned " + book.getName() +". Remaining books to return is: " + borrowersCollection.get(borrower).size());
            System.out.println("**********************************************************");
            return true;
        }
        return false;
    }

    public int totalBooksTaken(Book book){

        // Returns the total number of copies of a particular book that has been taken from the library
        if(!noOfCopies.containsKey(book)){
            System.out.println(book.getName() + " is not yet in our collections");
            return 0;
        }
        int taken = noOfCopies.get(book) - availableCopies.get(book);
        System.out.println("**********************************************************");
        System.out.println((taken == 0 ? "zero" : taken)  +" \"" + book.getName() + "\" " + (taken < 2 ? "book has been " : "books have been ") + "taken from the library\n" +
                "total \"" +  book.getName() +  "\" books available: " + availableCopies.get(book));
        System.out.println("**********************************************************");
        return taken;
    }

    public boolean addToQueue(Borrower borrower){
        return addToList(borrower, "addToQueue");
    }

    public Borrower selectFromQueue(Book book){
        return selectFromList(book, "selectFromQueue");
    }

    private boolean addToList(Borrower borrower, String typeOfOperation){
        // Adds the potential borrower to the list or queue depending on the types of operation it is being called to perform
        if(!(borrowersCollection.containsKey(borrower)) || borrowersCollection.get(borrower).size() < 3){
            if (typeOfOperation.equals("makeRequest")) {
                requester.add(borrower);
            } else {
                queue.add(borrower);
            }
            if(!(borrowersCollection.containsKey(borrower))){
                ArrayList<Book> book = new ArrayList<>();
                borrowersCollection.put(borrower, book);
            }
            return true;
        }
        System.out.println(borrower.getFullName() + " is not eligible to borrow books from the library");
        if (typeOfOperation.equals("makeRequest")) {
            requester.remove(borrower);
        } else {
            queue.remove(borrower);
        }
        return false;
    }

    private Borrower selectFromList(Book book, String typeOfOperation){
        //If there is no one in list, no books will be given out
        if(typeOfOperation.equals("borrowBook")){
            if(requester.peek() == null){
                return null;
            }
        }else if(queue.peek() == null){
            return null;
        }

        Borrower person = null;

        //If the book to be borrowed is not in library's collection, then no book is handed out
        if(!availableCopies.containsKey(book)){
            System.out.println("\"" + book.getName() + "\" is not yet part of this library's collection.");
            return null;
        }
        int copiesRemaining = availableCopies.get(book);

        //If book required from the library is currently available, then it can be given out to the first person
        if(copiesRemaining > 0){
            person = typeOfOperation.equals("borrowBook") ? requester.poll() : queue.poll();

            //If the first person has borrowed a similar copy, deny the request
            if(borrowersCollection.containsKey(person) && borrowersCollection.get(person).contains(book)){
                assert person != null;
                System.out.println(person.getFullName() + ", you already have this book in your list of collections");
                if (typeOfOperation.equals("borrowBook")) {
                    requester.remove(person);
                } else {
                    queue.remove(person);
                }
                return person;
            }
            availableCopies.put(book, copiesRemaining - 1);
            if (typeOfOperation.equals("borrowBook")) {
                requester.remove(person);
            } else {
                queue.remove(person);
            }
            ArrayList<Book> collect = borrowersCollection.get(person);
            collect.add(book);
            borrowersCollection.put(person, collect);
            System.out.println("**********************************************************");
            assert person != null;
            System.out.println(person.getFullName() + " has borrowed " + "\"" + book.getName() + "\""+ " from the library, \n"
                    + "Total number of books borrowed by " +  person.getFullName() +": "
                    + borrowersCollection.get(person).size() + ", total number of \"" + book.getName() + "\" available: " +
                    (availableCopies.get(book)));
            System.out.println("**********************************************************");
        }else{
            System.out.println("\"" + book.getName() + "\" not in shelf.");
        }
        return person;
    }
}
