package cs2110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookSorter {
    /**
     * Parse the file of book data
     * @param filename - input filename relative to current working directory
     * @precondition - input file exists and has:
     *    - a header row "Title, Author" and 1 or more rows of data
     *    - data rows include a title followed by a comma followed by an author
     *    - title and author do not contain commas
     *    - no lines besides header and data
     * @return an array with one string array for each book
     *    - a book is represented as an array whose first element is title
     *      and whose second element is author
     */
    public static Book[] parseBookList(String filename) {
        File inputFile = new File(filename).getAbsoluteFile();
        System.out.println(inputFile.getAbsolutePath());
        try {
            Scanner lineCounter = new Scanner(inputFile);
            int numLines;
            for (numLines = 0; lineCounter.hasNextLine(); numLines++) {
                lineCounter.nextLine();
            }
            lineCounter.close();

            Scanner dataScanner = new Scanner(inputFile);
            dataScanner.nextLine(); // ignore header row
            Book[] ret = new Book[numLines-1];
            for (int i = 0; dataScanner.hasNextLine(); i++) {
                String line = dataScanner.nextLine();
                String[] parts = line.split(",");
                ret[i] = new Book(parts[0], parts[1]);
            }
            dataScanner.close();
            return ret;
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return new Book[] {};
    }

    /**
     * Data class representing a book
     * @param title - the name of the book
     * @param author - string representing the full name(s) of the author(s) of the book
     */
    record Book(String title, String author) {
        /**
         * @return a String representing this Book
         */
        public String toString() {
            // this looks nicer than the default implementation of Record.toString
            return title() + " by " + author();
        }
    }

    /**
     * Finds the index of the element that should be in the first position of a
     * specific portion of the array recursively.
     */
    private static int selectFirst(Book[] books, int start, int firstSoFar, int ordering) {
        if (start >= books.length)
            return firstSoFar;

        boolean change = false;
        if (ordering == 1)
            change = books[start].title().compareTo(books[firstSoFar].title()) < 0;
        else if (ordering == 2)
            change = books[start].author().compareToIgnoreCase(books[firstSoFar].author()) < 0;
        if (change == true)
            firstSoFar = start;

        return selectFirst(books, start + 1, firstSoFar, ordering);
    }

    /**
     * Sorts the array using selection sort through recursion.
     */
    public static void selectionSort(Book[] books, int ordering) {
        if (books == null || books.length <= 1)
            return;
        if (!isValidArray(books, 0))
            return;
        selectionSortHelper(books, 0, ordering);
    }

    /**
     * Recursively checks if the array is valid meaning no null elements or strings.
     */
    private static boolean isValidArray(Book[] books, int index) {
        if (index >= books.length)
            return true;
        if (books[index] == null || books[index].title() == null || books[index].author() == null)
            return false;
        return isValidArray(books, index + 1);
    }

    /**
     * Helper method for selection sort.
     */
    private static void selectionSortHelper(Book[] books, int currentIndex, int ordering) {
        if (currentIndex >= books.length - 1)
            return;

        int minIndex = selectFirst(books, currentIndex, currentIndex, ordering);
        if (minIndex != currentIndex) {
            Book temp = books[currentIndex];
            books[currentIndex] = books[minIndex];
            books[minIndex] = temp;
        }
        selectionSortHelper(books, currentIndex + 1, ordering);
    }

    public static void main(String[] args) {
        // Edit this filename to use a different data set
        String filename = "data/books.csv";

        Book[] books = parseBookList(filename);
        System.out.println("=== Before Sorting ===");
        for(int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }
        selectionSort(books, 1);

        System.out.println();
        System.out.println("=== After Sorting ===");
        for(int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }
    }
}