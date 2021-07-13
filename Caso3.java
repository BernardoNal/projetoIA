package principal;

public class Caso3 extends Caso {
	@Override
	void addDisciplinas() {
		addDisciplina("ELET0043_dia1", SEGUNDA, 13, 2);
		addDisciplina("ESTAT0011_dia1", SEGUNDA, 15, 2);
		addDisciplina("COMP0415_dia1", SEGUNDA, 17, 2);
		addDisciplina("ESTAT0011_dia2", QUARTA, 15, 2);
		addDisciplina("COMP0415_dia2", QUARTA, 17, 2);
		addDisciplina("MAT0096_dia1", TERCA, 13, 2);
		addDisciplina("MAT0096_dia2", QUINTA, 13, 2);
		addDisciplina("COMP0409_dia1", TERCA, 15, 2);
		addDisciplina("COMP0409_dia2", QUINTA, 15, 2);
		addDisciplina("COMP0412_dia1", TERCA, 17, 2);
		addDisciplina("COMP0412_dia2", QUINTA, 17, 2);
		addDisciplina("MAT0154_dia1", QUARTA, 13, 2);
		addDisciplina("MAT0154_dia2", SEXTA, 13, 2);
		addDisciplina("COMP0417", SEXTA, 17, 2);
	}
	
	public Caso3() {
		super();
	}
}
