package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MembreTest {

    private Membre membre;

    @BeforeEach
    void setUp() {
        membre = new Membre(1, "Doe", "John", "password123", 25, "123 Main St");
    }

    @Test
    void testDefaultConstructor() {
        Membre defaultMembre = new Membre();
        assertNotNull(defaultMembre, "Default constructor should create a non-null instance.");
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(1, membre.getUid(), "UID should be initialized correctly.");
        assertEquals("Doe", membre.getLastName(), "Last name should be initialized correctly.");
        assertEquals("John", membre.getFirstName(), "First name should be initialized correctly.");
        assertEquals("password123", membre.getPassword(), "Password should be initialized correctly.");
        assertEquals(25, membre.getAge(), "Age should be initialized correctly.");
        assertEquals("123 Main St", membre.getAdresse(), "Address should be initialized correctly.");
    }

    @Test
    void testGetAndSetPenalized() {
        assertFalse(membre.isPenalized(), "Penalized should be false by default.");
        membre.setPenalized(true);
        assertTrue(membre.isPenalized(), "Penalized should be true after setting it to true.");
    }

    @Test
    void testGetAndSetFinPenalite() {
        Date finPenalite = Date.valueOf("2024-12-31");
        membre.setFinPenalite(finPenalite);
        assertEquals(finPenalite, membre.getFinPenalite(), "FinPenalite should return the set value.");
    }

    @Test
    void testGetFullName() {
        assertEquals("Doe John", membre.getFullName(), "Full name should be 'LastName FirstName'.");
    }
}
