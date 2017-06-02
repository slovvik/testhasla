public class Main {

    public static void main(String[] args) {

        PasswordService passwordService = new PasswordService();
        passwordService.providePassword();
        passwordService.testPassword();
    }
}
