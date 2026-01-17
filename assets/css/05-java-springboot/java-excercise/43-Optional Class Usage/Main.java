import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Optional<String> optional = Optional.ofNullable(null);
        System.out.println(optional.orElse("default"));
    }
}