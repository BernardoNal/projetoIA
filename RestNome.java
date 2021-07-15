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
		String nome1 = var1.getName().toUpperCase();
		String nome2 = var2.getName().toUpperCase();
		
		if (nome1.equals("PIBIC"))
			return (nome2.equals("PIBIT") || nome2.equals("PIBIX"));
		if (nome1.equals("PIBIT"))
			return (nome2.equals("PIBIC") || nome2.equals("PIBIX"));
		
		if (nome1.equals("ESTÁGIO"))
			return (nome2.equals("TRABALHO"));
		if (nome1.equals("TRABALHO"))
			return (nome2.equals("ESTÁGIO"));
		
		return false;
	}
}
