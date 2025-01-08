package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LivreTest {

    private Livre livre;

    @BeforeEach
    void setUp() {
        livre = new Livre("The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction", 5);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("The Catcher in the Rye", livre.getTitre());
        assertEquals("J.D. Salinger", livre.getAuteur());
        assertEquals(1951, livre.getAnneepub());
        assertEquals("Fiction", livre.getGenre());
        assertEquals(5, livre.getNbCopies());

        livre.setTitre("1984");
        livre.setAuteur("George Orwell");
        livre.setAnneepub(1949);
        livre.setGenre("Dystopian");
        livre.setNbCopies(10);

        assertEquals("1984", livre.getTitre());
        assertEquals("George Orwell", livre.getAuteur());
        assertEquals(1949, livre.getAnneepub());
        assertEquals("Dystopian", livre.getGenre());
        assertEquals(10, livre.getNbCopies());
    }

    @Test
    void testToString() {
        String expected = "1,The Catcher in the Rye,J.D. Salinger,1951,Fiction,5";
        assertEquals(expected, livre.toString());
    }

    @Test
    void testEquals() {
        Livre livre2 = new Livre("The Catcher in the Rye", "J.D. Salinger", 1951, "Fiction", 5);

        Livre livre3 = new Livre("1984", "George Orwell", 1949, "Dystopian", 10);
        assertFalse(livre.equals(livre3));

        assertTrue(livre.equals(livre));

        assertFalse(livre.equals(new String("Not a Livre")));
    }

    @Test
    void testConstructor() {
        Livre livreConstructor = new Livre("Brave New World", "Aldous Huxley", 1932, "Dystopian", 7);

        assertEquals("Brave New World", livreConstructor.getTitre());
        assertEquals("Aldous Huxley", livreConstructor.getAuteur());
        assertEquals(1932, livreConstructor.getAnneepub());
        assertEquals("Dystopian", livreConstructor.getGenre());
        assertEquals(7, livreConstructor.getNbCopies());
    }

    @Test
    void testIdGeneration() {
        Livre livre1 = new Livre("Book 1", "Author 1", 2000, "Genre", 3);
        Livre livre2 = new Livre("Book 2", "Author 2", 2005, "Genre", 4);

        assertNotEquals(livre1.getidBook(), livre2.getidBook());

    }
}
