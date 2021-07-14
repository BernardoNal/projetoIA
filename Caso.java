package principal;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;
import aima.core.search.csp.examples.NotEqualConstraint;

public abstract class Caso extends CSP<Variable, Horario> {
	public static final int SEGUNDA = 0;
	public static final int TERCA   = 1;
	public static final int QUARTA  = 2;
	public static final int QUINTA  = 3;
	public static final int SEXTA   = 4;
	public static final int SABADO  = 5;
	
	public Caso() {
		addDisciplinas();
		
		for (Variable var1 : getVariables())
			for (Variable var2 : getVariables())
				if (var1 != var2)
					addConstraint(new NotEqualConstraint<>(var1, var2));
	}
	
	void add(String nome, int dia_l, int dia_r, int hora_l, int hora_r, int duracao) {
		Variable tarefa = new Variable(nome);
		addVariable(tarefa);
		
		List<Horario> horarios = new ArrayList<>();
		// segunda a sexta
		for (int dia = dia_l; dia <= Math.min(SEXTA, dia_r); dia++) {
			for (int hora = hora_l; hora <= hora_r; hora++) {
				horarios.add(new Horario(dia, hora, duracao));
			}
		}
		// sábado
		if (dia_r == SABADO) {
			for (int hora = hora_l; hora <= Math.min(hora_r, 24 - duracao); hora++) {
				horarios.add(new Horario(SABADO, hora, duracao));
			}
		}
		
		setDomain(tarefa, new Domain<>(horarios));
		
		for (Variable var : getVariables())
			if (tarefa != var) {
				addConstraint(new NotEqualConstraint<>(tarefa, var));
				addConstraint(new NotEqualConstraint<>(var, tarefa));
			}
	}
	
	void addDisciplina(String nome, int dia, int inicio, int duracao) {
		add(nome, dia, dia, inicio, inicio, duracao);
	}
	
	void addTarefaDiaria(String nome, int inicio, int duracao) {
		for (int dia = SEGUNDA; dia <= SEXTA; dia++)
			add(nome + "_" + dia, dia, dia, inicio, inicio, duracao);
		add(nome + "_" + SABADO, SABADO, SABADO, inicio, inicio, duracao - ((inicio + duracao) / 24) * ((inicio + duracao) % 24));
		if ((inicio + duracao) / 24 == 1)
			add(nome + "_" + (SABADO + 1), SEGUNDA, SEGUNDA, 0, 0, (inicio + duracao) % 24);
	}
	
	void addTarefa(String nome, int duracao) {
		add(nome, SEGUNDA, SABADO, 0, 23, duracao);
	}
	
	abstract void addDisciplinas();
}
