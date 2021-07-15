package principal;

public class Caso2 extends Caso {
	@Override
	void addDisciplinas() {
		addDisciplina("* COMP0409", SEGUNDA, 13, 2);
		addDisciplina("* COMP0409", QUARTA, 13, 2);
		addDisciplina("* COMP0408", SEGUNDA, 17, 2);
		addDisciplina("* COMP0408", QUARTA, 17, 2);
		addDisciplina("* COMP0461", SEGUNDA, 20, 2);
		addDisciplina("* COMP0461", QUARTA, 20, 2);
		addDisciplina("* COMP0412", TERCA, 15, 2);
		addDisciplina("* COMP0412", QUINTA, 15, 2);
		addDisciplina("* COMP0438", SEXTA, 13, 4);
	}
	
	public Caso2() {
		super();
	}
}