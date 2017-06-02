import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordService {

    private Password password;

    public String getPassword() {
        return password.getPassword();
    }

    public PasswordService() {
    }

    public PasswordService(Password password) {
        this.password = password;
    }

    private static final int MIN_PASSWORD_LENGTH = 8;

    private static final List<String> LAST_THREE_PASSWORDS = Arrays.asList(
            "Haslo12#",
            "#12Haslo",
            "Ha12#slo"
    );

    private static final List<String> CONDITIONS = Arrays.asList(
            "Min 8 znaków",
            "Min jedna duża litera",
            "Min jedna cyfra",
            "Nie ma znaków narodowych",
            "Róźne od trzech ostatnich haseł",
            "Brak białych zanków",
            "Puste hasło",
            "Min jeden znak specjalny"
    );

    private final List<BooleanSupplier> TEST_METHODS = Arrays.asList(
            (BooleanSupplier) this::minLength,
            (BooleanSupplier) this::atLeastOneUpperCase,
            (BooleanSupplier) this::atLeastOneNumber,
            (BooleanSupplier) this::noNationalCharacters,
            (BooleanSupplier) this::differentThatLastThreePassword,
            (BooleanSupplier) this::noWhiteSpaces,
            (BooleanSupplier) this::emptyPassword,
            (BooleanSupplier) this::atLeastOneSpecialCharacters

    );

    private static Map<String, BooleanSupplier> testMap;

    private void addTestMethods() {
        testMap = new HashMap<>();
        for (int i = 0; i < TEST_METHODS.size(); i++) {
            final int method = i;
            testMap.put(CONDITIONS.get(i), () -> TEST_METHODS.get(method).getAsBoolean());
        }
    }

    public boolean emptyPassword() {
        return getPassword().length() != 0;
    }

    public boolean minLength() {
        return getPassword().length() >= MIN_PASSWORD_LENGTH;
    }

    public boolean atLeastOneUpperCase() {
        return !getPassword().equals(getPassword().toLowerCase());
    }

    public boolean atLeastOneSpecialCharacters() {
        Pattern p = Pattern.compile("[^a-z0-9;/]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(getPassword());
        return m.find();
    }

    public boolean atLeastOneNumber() {
        return !getPassword().matches("[0-9]");
    }

    public boolean noNationalCharacters() {
        return !getPassword().matches("[ąćęłńóśźżĄĆĘŁŃÓŚŹŻ]");
    }

    public boolean differentThatLastThreePassword() {
        for (String password : LAST_THREE_PASSWORDS) {
            if (getPassword().equals(password)) return false;
        }
        return true;
    }

    public boolean noWhiteSpaces() {
        return !getPassword().contains(" ") && !getPassword().contains("\n");
    }

    public void providePassword() {
        System.out.print("Proszę podaj hasło: ");
        Scanner scanner = new Scanner(System.in);
        String stringPassword = scanner.nextLine();
        System.out.println("Twoje hasło: " + stringPassword);
        password = new Password(stringPassword);
    }

    public void testPassword() {
        addTestMethods();
        boolean correct = true;
        for (int i = 0; i < TEST_METHODS.size(); i++) {
            if (!testMap.get(CONDITIONS.get(i)).getAsBoolean()) {
                System.out.println(CONDITIONS.get(i) + ": warunek nie spełniony");
                correct = false;
            } else {
                System.out.println(CONDITIONS.get(i) + ": warunek spełniony");
            }
        }
        if (correct) System.out.println("Hasło poprawne");
        else System.out.println("Hasło niepoprawne");
    }
}