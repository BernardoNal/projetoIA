package principal;

public class Caso3 extends Caso {
	@Override
	void addDisciplinas() {
		addDisciplina("* ELET0043", SEGUNDA, 13, 2);
		addDisciplina("* ESTAT0011", SEGUNDA, 15, 2);
		addDisciplina("* COMP0415", SEGUNDA, 17, 2);
		addDisciplina("* ESTAT0011", QUARTA, 15, 2);
		addDisciplina("* COMP0415", QUARTA, 17, 2);
		addDisciplina("* MAT0096", TERCA, 13, 2);
		addDisciplina("* MAT0096", QUINTA, 13, 2);
		addDisciplina("* COMP0409", TERCA, 15, 2);
		addDisciplina("* COMP0409", QUINTA, 15, 2);
		addDisciplina("* COMP0412", TERCA, 17, 2);
		addDisciplina("* COMP0412", QUINTA, 17, 2);
		addDisciplina("* MAT0154", QUARTA, 13, 2);
		addDisciplina("* MAT0154", SEXTA, 13, 2);
		addDisciplina("* COMP0417", SEXTA, 17, 2);
	}
	
	public Caso3() {
		super();
	}
}