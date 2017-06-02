import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordTest {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private PasswordService passwordService;

    @Test
    public void passwordLengthEqualsMin() {
        passwordService = new PasswordService(new Password("Haslo12#"));
        assertEquals(MIN_PASSWORD_LENGTH, passwordService.getPassword().length());
    }

    @Test
    public void passwordLengthNotEqualsMin() {
        passwordService = new PasswordService(new Password("Haslo1#"));
        assertFalse(passwordService.minLength());
    }

    @Test
    public void passwordLengthGreaterThenMin() {
        passwordService = new PasswordService(new Password("Inne#99#haslo"));
        assertTrue(passwordService.minLength());
    }

    @Test
    public void passwordWithOneUpperCase() {
        passwordService = new PasswordService(new Password("Haslo12#"));
        assertTrue(passwordService.atLeastOneUpperCase());
    }

    @Test
    public void passwordWithNoneUpperCase() {
        passwordService = new PasswordService(new Password("haslo12#"));
        assertFalse(passwordService.atLeastOneUpperCase());
    }

    @Test
    public void passwordWithMoreThenOneUpperCase() {
        passwordService = new PasswordService(new Password("Inne#99#Haslo"));
        assertTrue(passwordService.atLeastOneUpperCase());
    }

    @Test
    public void passwordWithOneNumber() {
        passwordService = new PasswordService(new Password("Haslo12#"));
        assertTrue(passwordService.atLeastOneNumber());
    }

    @Test
    public void passwordWithoutNumber() {
        passwordService = new PasswordService(new Password("haslo#"));
        assertTrue(passwordService.atLeastOneNumber());
    }

    @Test
    public void passwordWithMoreThenOneNumber() {
        passwordService = new PasswordService(new Password("Inne#99#Haslo"));
        assertTrue(passwordService.atLeastOneNumber());
    }

    @Test
    public void passwordWithNationalCharacters() {
        passwordService = new PasswordService(new Password("Hasło12#"));
        assertTrue(passwordService.noNationalCharacters());
    }

    @Test
    public void passwordWithoutNationalCharacters() {
        passwordService = new PasswordService(new Password("haslo#"));
        assertTrue(passwordService.noNationalCharacters());
    }

    @Test
    public void passwordDifferentThatLastThreePassword() {
        passwordService = new PasswordService(new Password("Ha12#sloslo"));
        assertTrue(passwordService.differentThatLastThreePassword());
    }

    @Test
    public void passwordNoDifferentThatLastThreePassword() {
        passwordService = new PasswordService(new Password("Ha12#slo"));
        assertFalse(passwordService.differentThatLastThreePassword());
    }

    @Test
    public void passwordWithWhiteSpaces() {
        passwordService = new PasswordService(new Password("Has ło12#"));
        assertFalse(passwordService.noWhiteSpaces());
    }

    @Test
    public void passwordWithoutWhiteSpaces() {
        passwordService = new PasswordService(new Password("haslo#"));
        assertTrue(passwordService.noWhiteSpaces());
    }

    @Test
    public void emptyPassword() {
        passwordService = new PasswordService(new Password(""));
        assertFalse(passwordService.emptyPassword());
    }

    @Test
    public void noEmptyPassword() {
        passwordService = new PasswordService(new Password("haslo#"));
        assertTrue(passwordService.emptyPassword());
    }

    @Test
    public void passwordWithAtLeastOneSpecialCharacters() {
        passwordService = new PasswordService(new Password("Ha12#slo"));
        assertTrue(passwordService.atLeastOneSpecialCharacters());
    }

    @Test
    public void passwordWithoutAtLeastOneSpecialCharacters() {
        passwordService = new PasswordService(new Password("Ha12slo"));
        assertFalse(passwordService.atLeastOneSpecialCharacters());
    }

}


