package principal;

import java.util.Arrays;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import aima.core.search.csp.examples.NotEqualConstraint;

public class Caso1 extends Caso {
	@Override
	void addDisciplinas() {
		addDisciplina("COMP0408_dia1", SEGUNDA, 21, 2);
		addDisciplina("COMP0408_dia2", QUARTA, 21, 2);
		addDisciplina("COMP0455_dia1", TERCA, 15, 2);
		addDisciplina("COMP0455_dia2", QUINTA, 15, 2);
		addDisciplina("COMP0481", QUINTA, 19, 2);
	}
	
	public Caso1() {
		super();
	}
}
