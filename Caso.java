package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	Map<String, Integer> map_tarefas = new HashMap<String, Integer>();
	
	int get_key(String nome) {
		int key;
		if (map_tarefas.get(nome) == null) {
			key = 1;
			map_tarefas.put(nome, 1);
		} else {
			key = map_tarefas.get(nome) + 1;
			map_tarefas.remove(nome);
			map_tarefas.put(nome, key);
		}
		return key;
	}
	
	public Caso() {
		addDisciplinas();
		
		for (Variable var1 : getVariables())
			for (Variable var2 : getVariables())
				if (var1 != var2)
					addConstraint(new NotEqualConstraint<>(var1, var2));
	}
	
	void add(String nome, int dia_l, int dia_r, float hora_l, float hora_r, float duracao) {
		Variable tarefa = new Variable(nome);
		addVariable(tarefa);
		
		List<Horario> horarios = new ArrayList<>();
		// segunda a sexta
		for (int dia = dia_l; dia <= Math.min(SEXTA, dia_r); dia++) {
			for (float hora = hora_l; hora <= hora_r; hora += 0.5) {
				horarios.add(new Horario(dia, hora, duracao));
			}
		}
		// sábado
		if (dia_r == SABADO) {
			for (float hora = hora_l; hora <= Math.min(hora_r, 24 - duracao); hora += 0.5) {
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
	
	void addDisciplina(String nome, int dia, float inicio, float duracao) {
		add(nome + '_' + get_key(nome), dia, dia, inicio, inicio, duracao);
	}
	
	void addTarefaDiaria(String nome, float inicio, float duracao) {
		for (int dia = SEGUNDA; dia <= SEXTA; dia++)
			add(nome + "_" + dia, dia, dia, inicio, inicio, duracao);
		add(nome + "_" + SABADO, SABADO, SABADO, inicio, inicio, duracao - ((int)(inicio + duracao) / 24) * ((inicio + duracao) % 24));
		if ((int)(inicio + duracao) / 24 == 1)
			add(nome + "_" + (SABADO + 1), SEGUNDA, SEGUNDA, 0, 0, (inicio + duracao) % 24);
	}
	
	void addTarefaDia(String nome, int dia, float duracao) {
		
		add(nome + '_' + get_key(nome), dia, dia, 0, 24 - duracao, duracao);
	}
	
	void addTarefa(String nome, float duracao) {
		add(nome + '_' + get_key(nome), SEGUNDA, SABADO, 0, 24 - duracao, duracao);
	}
	
	abstract void addDisciplinas();
}
