package cmpt.sat;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Model;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

public class ArrayExample {
    public static void main(String[] args) {
        // Create a new context
        Context ctx = new Context();
        // Create boolean variables
        int m = 3;
        int n = 2;
        BoolExpr[][] p = new BoolExpr[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                p[i][j] = ctx.mkBoolConst("p_" + i + "_" + j);
            }
        }
        // Create a solver
        Solver solver = ctx.mkSolver();
        // Add formulas: note the connection between var-length arg and array
        // (p00 \/ p01)  /\ (p10 \/ p11) /\ (p20 \/ p21)
        for (int i = 0; i < m; ++i ) {
            solver.add(ctx.mkOr(p[i]));
        }
        // or you can write in this way
        // for (int i = 0; i < m; ++i) {
        //    BoolExpr f = ctx.mkFalse();
        //    for (int j = 0; j < n; ++j) {
        //        f = ctx.mkOr(p[i][j], f);
        //    }
        //    solver.add(f);
        // }
        // Check satisfiability
        Status status = solver.check();
        // Get a model
        Model model = solver.getModel();
        System.out.println(model);
    }
}
