package com.javarush.task.task15.task1504;

import java.util.LinkedList;
import java.util.List;

/* 
ООП - книги
*/

public class Solution {
    public static void main(String[] args) {
        List<Book> books = new LinkedList<>();
        books.add(new MarkTwainBook("Tom Sawyer"));
        books.add(new AgathaChristieBook("Hercules Poirot"));
        System.out.println(books);
    }

    abstract static class Book {
        private final String author;

        public Book(String author) {
            this.author = author;
        }

        public abstract Book getBook();

        public abstract String getTitle();

        private String getOutputByBookType() {
            String agathaChristieOutput = author + ": " + getBook().getTitle() + " is a detective";
            String markTwainOutput = getBook().getTitle() + " was written by " + author;

            //Add your code here

            if (getBook() instanceof MarkTwainBook) {
                return markTwainOutput;
            } else return agathaChristieOutput;
        }

        public String toString() {
            return getOutputByBookType();
        }
    }

    public static class MarkTwainBook extends Book {
        private final String title;

        public MarkTwainBook(String author) {
            super("Mark Twain");
            title = author;
        }

        @Override
        public MarkTwainBook getBook() {
            return this;
        }

        @Override
        public String getTitle() {
            return title;
        }
    }

    public static class AgathaChristieBook extends Book {

        private final String title;

        public AgathaChristieBook(String author) {
            super("Agatha Christie");
            title = author;
        }

        @Override
        public AgathaChristieBook getBook() {
            return this;
        }

        @Override
        public String getTitle() {
            return title;
        }
    }
}
