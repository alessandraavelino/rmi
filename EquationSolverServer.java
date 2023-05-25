import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EquationSolverServer implements EquationSolver {
    public double[] solveQuadraticEquation(double a, double b, double c) throws RemoteException {
        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            System.out.println("Equação resolvida: Raiz 1 = " + root1 + ", Raiz 2 = " + root2);

            return new double[]{root1, root2};
        } else if (discriminant == 0) {
            double root = -b / (2 * a);

            System.out.println("Equação resolvida: Raiz única = " + root);

            return new double[]{root, root};
        } else {
            // Raízes complexas
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);

            System.out.println("Equação resolvida: Raiz 1 = " + realPart + " + " + imaginaryPart + "i, " +
                    "Raiz 2 = " + realPart + " - " + imaginaryPart + "i");

            return new double[]{realPart, imaginaryPart, realPart, -imaginaryPart};
        }
    }

    public static void main(String[] args) {
        try {
            EquationSolverServer server = new EquationSolverServer();
            EquationSolver stub = (EquationSolver) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("EquationSolver", stub);

            System.out.println("Servidor pronto para receber solicitações.");

            // Aguarda o encerramento do servidor
            System.out.println("Pressione Enter para encerrar o servidor.");
            System.in.read();

            UnicastRemoteObject.unexportObject(server, true);

            System.out.println("Servidor encerrado.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
