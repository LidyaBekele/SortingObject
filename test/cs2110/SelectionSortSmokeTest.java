package cs2110;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs2110.BookSorter.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Smoke test suite for Selection Sort
 *
 * The smoke tests ensure that your code runs and works as expected for a few simple cases.
 * The tests provided here are the ones that will run on gradescope every time you
 * upload your code.
 *
 * As a reminder, smoke tests are not a complete test suite. Passing all smoke tests does not
 * mean that your code has entirely correct behavior. We will run additional hidden autograder
 * tests on your code after the homework submission deadline to further verify its correctness.
 *
 * You should add more tests to this file to gain a greater level of confidence in your code!
 */
public class SelectionSortSmokeTest {

    @DisplayName("WHEN this list of books has one element, THEN sorting with ordering 1"
            + "yields the same list")
    @Test
    void testEmptyOrdering1() {
        Book[] input = new Book[] {new Book("Race After Technology", "Ruha Benjamin")};

        BookSorter.selectionSort(input, 1);

        // Input has been modified. We are now thinking of it as actual output.
        Book[] actual = input;

        Book[] expected = new Book[] {new Book("Race After Technology", "Ruha Benjamin")};
        assertArrayEquals(actual, expected);
    }

    @DisplayName("WHEN this list of books has one element, THEN sorting with ordering 2"
            + "yields the same list")
    @Test
    void testEmptyOrdering2() {
        Book[] input = new Book[] {new Book("Race After Technology", "Ruha Benjamin")};

        BookSorter.selectionSort(input, 2);

        // Input has been modified. We are now thinking of it as actual output.
        Book[] actual = input;

        Book[] expected = new Book[] {new Book("Race After Technology", "Ruha Benjamin")};
        assertArrayEquals(actual, expected);
    }

    @DisplayName("WHEN this list of multiple books is already sorted, THEN sorting with ordering 1 "
            + "yields the same list")
    @Test
    void testAlreadySortedTitle() {
        Book[] input = new Book[] {
                new Book("Invisible Women", "Caroline Criado Perez"),
                new Book("Race After Technology", "Ruha Benjamin"),
                new Book("Weapons of Math Destruction", "Cathy O'Neil")
        };

        BookSorter.selectionSort(input, 1);

        // Input has been modified. We are now thinking of it as actual output.
        Book[] actual = input;

        Book[] expected = new Book[] {
                new Book("Invisible Women", "Caroline Criado Perez"),
                new Book("Race After Technology", "Ruha Benjamin"),
                new Book("Weapons of Math Destruction", "Cathy O'Neil")
        };
        assertArrayEquals(actual, expected);
    }


    @DisplayName("WHEN this list has duplicates, THEN the output list is in order for ordering 2")

    @Test
    void testDuplicatesAuthor() {
        Book[] input = new Book[] {
                new Book("Viral Justice", "Ruha Benjamin"),
                new Book("Invisible Women", "Caroline Criado Perez"),
                new Book("Race After Technology", "Ruha Benjamin"),
                new Book("Weapons of Math Destruction", "Cathy O'Neil"),
                new Book("Imagination: A Manifesto", "Ruha Benjamin"),
        };

        BookSorter.selectionSort(input, 2);

        // Input has been modified. We are now thinking of it as actual output.
        Book[] actual = input;

        // This tests a property of the output array rather than testing it for equality
        // with an expected array.
        for (int i = 0; i < input.length-1; i++) {
            // we want actual[i+1] to have author string greater than or equal to actual[i]
            // so the output should be negative or zero
            assertTrue(actual[i].author().compareToIgnoreCase(actual[i+1].author()) <= 0);
        }

    }

}
