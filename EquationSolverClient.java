import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class EquationSolverClient {
    public static void main(String[] args) {
        try {
            // Obtém o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtém o stub do servidor
            EquationSolver solver = (EquationSolver) registry.lookup("EquationSolver");

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Digite o valor de a: ");
                double a = scanner.nextDouble();

                System.out.print("Digite o valor de b: ");
                double b = scanner.nextDouble();

                System.out.print("Digite o valor de c: ");
                double c = scanner.nextDouble();

                // Chama o método remoto para resolver a equação de segundo grau
                double[] roots = solver.solveQuadraticEquation(a, b, c);

                // Exibe as raízes
                System.out.println("Raiz 1: " + roots[0]);
                System.out.println("Raiz 2: " + roots[1]);
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
