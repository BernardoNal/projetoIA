package principal;

public class Caso2 extends Caso {
	@Override
	void addDisciplinas() {
		addDisciplina("COMP0409_dia1", SEGUNDA, 13, 2);
		addDisciplina("COMP0409_dia2", QUARTA, 13, 2);
		addDisciplina("COMP0408_dia1", SEGUNDA, 17, 2);
		addDisciplina("COMP0408_dia2", QUARTA, 17, 2);
		addDisciplina("COMP0461_dia1", SEGUNDA, 20, 2);
		addDisciplina("COMP0461_dia2", QUARTA, 20, 2);
		addDisciplina("COMP0412_dia1", TERCA, 15, 2);
		addDisciplina("COMP0412_dia2", QUINTA, 15, 2);
		addDisciplina("COMP0438", SEXTA, 13, 4);
	}
	
	public Caso2() {
		super();
	}
}
