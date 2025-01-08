package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Doe", "John", "password123", 30, "123 Main Street");
    }

    @Test
    void testDefaultConstructor() {
        User defaultUser = new User();
        assertNotNull(defaultUser, "Default constructor should create a non-null instance.");
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("Doe", user.getLastName(), "Last name should be initialized correctly.");
        assertEquals("John", user.getFirstName(), "First name should be initialized correctly.");
        assertEquals("password123", user.getPassword(), "Password should be initialized correctly.");
        assertEquals(30, user.getAge(), "Age should be initialized correctly.");
        assertEquals("123 Main Street", user.getAdresse(), "Address should be initialized correctly.");
    }

    @Test
    void testGetAndSetUid() {
        user.setUid(42);
        assertEquals(42, user.getUid(), "UID should be set correctly.");
    }

    @Test
    void testGetAndSetAge() {
        user.setAge(35);
        assertEquals(35, user.getAge(), "Age should be set correctly.");
    }

    @Test
    void testGetAndSetAdresse() {
        user.setAdresse("456 Elm Street");
        assertEquals("456 Elm Street", user.getAdresse(), "Address should be set correctly.");
    }

    @Test
    void testGetAndSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword(), "Password should be set correctly.");
    }

    @Test
    void testGetAndSetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName(), "Last name should be set correctly.");
    }

    @Test
    void testGetAndSetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName(), "First name should be set correctly.");
    }

    @Test
    void testToString() {
        String expected = "1,Doe,John,password123,30,123 Main Street";
        String result = user.toString();
        assertNotNull(result, "toString() should return a non-null string.");
        assertTrue(result.contains("Doe") && result.contains("John"), "toString() should include key fields.");
    }
}
