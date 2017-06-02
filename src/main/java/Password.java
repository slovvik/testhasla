public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Password[" +
                "password='" + password + '\'' +
                ']';
    }
}

