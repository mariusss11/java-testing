package ContactTests;

import org.example.contact.ContactManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public static void setupAll(){
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should Create Contact")
    public void shouldCreateContact(){
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty()); //verify the list is not empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size()); //make sure is one element (the one we added)
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                    contact.getLastName().equals("Doe") &&
                    contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null") //provide a custom readable name to the method in the test results
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () ->
            contactManager.addContact(null, "Doe", "0123456789")
        );
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () ->
            contactManager.addContact("John", null, "0123456789")
        );
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class, () ->
            contactManager.addContact("John", "Doe", null)
        );
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Should be executed at the end of the Test");
    }

    @Test
    @DisplayName("Should Create Contact Only on MACOS")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enable only on MAC OS")
    @Disabled
    public void shouldCreateContactOnlyOnMAC(){
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty()); //verify the list is not empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size()); //make sure is one element (the one we added)
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should Create Contact Only on Windows OS")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Enable only on Windows OS")
    public void shouldCreateContactOnlyOnWindows(){
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty()); //verify the list is not empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size()); //make sure is one element (the one we added)
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    @Disabled
    public void shouldTestContactOnDev() {
        Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV"))); //verify who is the one who takes the test
        contactManager.addContact("John", "Doe", "0123456789"); //and adds a person
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Method Source Case - Phone Number Should match the require Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberUsingMethodSource(String phoneNumber){
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList(){
        return Arrays.asList("0123456789", "0987654321", "0963258741");
    }

    @Nested
    class RepeatedNestedTests{
        @DisplayName("Repeat Contact Creation for 5 times")
        @RepeatedTest(value = 5,
                name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestContactCreationRepeatedly(){
            contactManager.addContact("John", "Doe", "0123456789"); //and adds a person
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @Nested
    class ParameterizedNestedTests{
        @DisplayName("Test Phone Numbers")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0987654321", "0123456789"})
        public void shouldTestContentCreationWithValueSources(String phoneNumber){
            contactManager.addContact("John", "Doe", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV Source Case - Phone Number Should match the require Format")
        @ParameterizedTest
        @CsvSource({"0123456789", "0987654321", "0123456789"})
        public void shouldTestPhoneNumberUsingCSVSource(String phoneNumber){
            contactManager.addContact("John", "Doe", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV File Source Case - Phone Number Should match the require Format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestPhoneNumberUsingCSVFileSource(String phoneNumber){
            contactManager.addContact("John", "Doe", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }


}
