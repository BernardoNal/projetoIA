package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Variable;
import aima.core.search.csp.solver.CspSolver;
import aima.core.search.csp.solver.FlexibleBacktrackingSolver;

public class Projeto {
	public static void linha_barra(int largura) {
		// horário
		for (int i = 0; i < 13; i++)
			System.out.print("_");
		System.out.print("_|_");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < largura; j++)
				System.out.print("_");
			System.out.print("_|_");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Digite o número do caso: ");
		int n_caso = in.nextInt();
		
		Caso caso;
		
		if (n_caso == 1) {
			caso = new Caso1();
		} else {
			System.out.println("Caso " + n_caso + " não existe.");
			return;
		}
		
		System.out.println("Para adicionar uma tarefa diária, digite o nome, o horário de início e a duração da tarefa.");
		System.out.println("Para parar de adicionar tarefas diárias, digite apenas 0");
		while (true) {
			String nome = in.next();
			if (nome.equals("0"))
				break;
			
			int inicio = in.nextInt();
			int duracao = in.nextInt();
			
			caso.addTarefaDiaria(nome, inicio, duracao);
		}
		
		System.out.println("Para adicionar uma tarefa, digite o nome e a duração da tarefa.");
		System.out.println("Para parar de adicionar tarefas, digite apenas 0");
		while (true) {
			String nome = in.next();
			if (nome.equals("0"))
				break;
			
			int duracao = in.nextInt();
			
			caso.addTarefa(nome, duracao);
		}
		
		String[][] tabela = new String[48][6];
		int largura = 3;
		
		CspSolver<Variable, Horario> algoritmo = new FlexibleBacktrackingSolver<Variable, Horario>().setAll();
		Optional<Assignment<Variable, Horario>> solucao = algoritmo.solve(caso);
		if (solucao.isPresent()) {
			for (Variable var : solucao.get().getVariables()) {
				Horario horario = solucao.get().getValue(var);
//				System.out.println(var.getName());
//				System.out.println("Dia: " + horario.dia);
//				System.out.println("Hora: " + horario.inicio);
//				System.out.println("Duração: " + horario.duracao);
//				System.out.println("");
				
				largura = Math.max(largura, var.getName().length());
				for (int i = horario.inicio * 2; i < horario.inicio * 2 + (horario.duracao * 2); i++) {
					System.out.println((i % 48) + " " + (horario.dia + (i / 48)));
					tabela[i % 48][horario.dia + (i / 48)] = var.getName(); 
				}
			}
		}
		
		for (int hora = 0; hora < 48; hora++) {
			//linha_barra(largura);
			
			String add_0_primeiro = "";
			String add_0_segundo = "";
			if (hora / 2 < 10) {
				add_0_primeiro = "0";
				add_0_segundo = "0";
			}
			if (hora % 2 == 1 && hora / 2 == 9) {
				add_0_segundo = "";
			}
			
			if (hora % 2 == 0)
				System.out.print(add_0_primeiro + (hora / 2) + ":00 - " + add_0_segundo + (hora / 2) + ":30");	
			else
				System.out.print(add_0_primeiro + (hora / 2) + ":30 - " + add_0_segundo + (hora / 2 + 1) + ":00");
			
			System.out.print(" | ");
			for (int dia = 0; dia < 6; dia++) {
				int falta = largura;
				if (tabela[hora][dia] != null) {
					System.out.print(tabela[hora][dia]);
					falta -= tabela[hora][dia].length();
				}
				for (int c = 0; c < falta; c++)
					System.out.print(" ");
				System.out.print(" | ");
			}
			System.out.println("");
		}
	}
}
