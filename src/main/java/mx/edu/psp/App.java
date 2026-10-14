package mx.edu.psp;

/** Command-line entry point. Usage: java ... App <x> <dof>. */
public final class App {
    private App() { }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java -jar simpson-mvn-1.0-SNAPSHOT.jar <x> <dof>");
            System.exit(2);
        }
        try {
            double x = Double.parseDouble(args[0]);
            int dof = Integer.parseInt(args[1]);
            System.out.println(new Logic().describe(x, dof));
        } catch (IllegalArgumentException | ArithmeticException exception) {
            System.err.println("Error: " + exception.getMessage());
            System.exit(2);
        }
    }
}
