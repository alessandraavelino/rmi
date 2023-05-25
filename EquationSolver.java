import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationSolver extends Remote {
    double[] solveQuadraticEquation(double a, double b, double c) throws RemoteException;
}
