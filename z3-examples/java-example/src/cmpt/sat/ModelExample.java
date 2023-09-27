package cmpt.sat;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Model;
import com.microsoft.z3.Solver;

public class ModelExample {
    public static void main(String[] args) {
        // Create a new context
        Context ctx = new Context();
        // Create boolean variables
        BoolExpr p = ctx.mkBoolConst("p");
        BoolExpr q = ctx.mkBoolConst("q");
        // Build formula p <-> q
        BoolExpr f = ctx.mkIff(p, q);
        // Create a solver
        Solver solver = ctx.mkSolver();
        // Add the formula
        solver.add(f);
        // Check satisfiability
        System.out.println(solver.check());
        // Get a model
        Model model = solver.getModel();
        System.out.println("p: " + model.getConstInterp(p));
        System.out.println("q: " + model.getConstInterp(q));
    }
}
