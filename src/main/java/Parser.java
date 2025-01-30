public class Parser {
    public Parser() {

    }

    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public static int parseMarkUnmarkCommand(String input) throws ArrayIndexOutOfBoundsException {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}
