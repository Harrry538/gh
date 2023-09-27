package cmpt.sat;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;

public class ValidityExample {
    public static void main(String[] args) {
        // Create a new context
        Context ctx = new Context();
        // Create boolean variables
        BoolExpr p = ctx.mkBoolConst("p");
        BoolExpr q = ctx.mkBoolConst("q");
        // Build formulas p -> (q -> p)
        BoolExpr f = ctx.mkImplies(p, ctx.mkImplies(q, p));
        // Check validity
        System.out.println("Is " + f + " valid? " + isValid(ctx, f));
    }

    public static boolean isValid(Context ctx, BoolExpr formula) {
        Solver solver = ctx.mkSolver();
        BoolExpr negation = ctx.mkNot(formula);
        solver.add(negation);
        return solver.check() == Status.UNSATISFIABLE;
    }

}
