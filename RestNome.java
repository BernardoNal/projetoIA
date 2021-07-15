package principal;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

/**
 * Represents a binary constraint which forbids equal values.
 * 
 * @author Ruediger Lunde
 */
public class RestNome<VAR extends Variable, VAL> implements Constraint<VAR, VAL> {

	private VAR var1;
	private VAR var2;
	private List<VAR> scope;

	public RestNome(VAR var1, VAR var2) {
		this.var1 = var1;
		this.var2 = var2;
		scope = new ArrayList<>(2);
		scope.add(var1);
		scope.add(var2);
	}

	@Override
	public List<VAR> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<VAR, VAL> assignment) {
		if (var1.getName().equals("PIBIC"))
			return (var2.getName().equals("PIBIT") || var2.getName().equals("PIBIX"));
		if (var1.getName().equals("PIBIT"))
			return (var2.getName().equals("PIBIC") || var2.getName().equals("PIBIX"));
		
		if (var1.getName().equals("ESTÁGIO"))
			return (var2.getName().equals("TRABALHO"));
		if (var1.getName().equals("TRABALHO"))
			return (var2.getName().equals("ESTÁGIO"));
		
		return false;
	}
}
