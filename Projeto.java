package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import aima.core.search.csp.*;
import aima.core.search.csp.examples.MapCSP;
import aima.core.search.csp.examples.NotEqualConstraint;
import aima.core.search.csp.solver.inference.AC3Strategy;
import aima.core.search.csp.solver.inference.ForwardCheckingStrategy;
import aima.core.search.csp.solver.*;

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
		
		switch (n_caso) {
	     case 1:
	    	 caso = new Caso1();
	       break;
	     case 2:
	    	 caso = new Caso2();
	       break;
	     case 3:
	    	caso = new Caso3();
	       break;
	     default:{
	    	 System.out.println("Caso " + n_caso + " não existe.");
				return;}
	  }
		
		System.out.println("Para adicionar uma tarefa diária, digite o nome, o horário de início e a duração da tarefa.");
		System.out.println("Para parar de adicionar tarefas diárias, digite apenas 0");
		while (true) {
			String nome = in.next();
			if (nome.equals("0"))
				break;
			
			float inicio = in.nextFloat();
			float duracao = in.nextFloat();
			
			caso.addTarefaDiaria(nome, inicio, duracao);
		}
		
		System.out.println("Para adicionar uma tarefa, digite o nome e a duração da tarefa.");
		System.out.println("Para parar de adicionar tarefas, digite apenas 0");
		while (true) {
			String nome = in.next();
			if (nome.equals("0"))
				break;
			
			float duracao = in.nextFloat();
			
			caso.addTarefa(nome, duracao);
		}
		
		System.out.println("Para adicionar uma tarefa de um dia, digite o nome, o dia e a duração da tarefa.");
		System.out.println("Para parar de adicionar tarefas, digite apenas 0");
		while (true) {
			String nome = in.next();
			if (nome.equals("0"))
				break;
			
			int dia = in.nextInt();
			float duracao = in.nextFloat();
			
			caso.addTarefaDia(nome, dia, duracao);
		}
		
		String[][] tabela = new String[48][6];
		int largura = 3;
		
		//CspSolver<Variable, Horario> algoritmo = new BackjumpingBacktrackingSolver<>();
		//CspSolver<Variable, Horario> algoritimo = new TreeCspSolver<>(); Não Funciona
		//CspSolver<Variable, Horario> algoritmo = new MinConflictsSolver<>(50);
		//CspSolver<Variable, Horario> algoritmo = new FlexibleBacktrackingSolver<>();
		CspSolver<Variable, Horario> algoritimo = new FlexibleBacktrackingSolver<Variable, Horario>().setAll();
		Optional<Assignment<Variable, Horario>> solucao = algoritimo.solve(caso);
		
           
       
		if (solucao.isPresent()) {
			for (Variable var : solucao.get().getVariables()) {
				Horario horario = solucao.get().getValue(var);
//				System.out.println(var.getName());
//				System.out.println("Dia: " + horario.dia);
//				System.out.println("Hora: " + horario.inicio);
//				System.out.println("Duração: " + horario.duracao);
//				System.out.println("");
				
				largura = Math.max(largura, var.getName().length());
				for (float i = horario.inicio * 2; i < horario.inicio * 2 + (horario.duracao * 2); i++) {
					System.out.println((i % 48) + " " + (horario.dia + (i / 48)));
					tabela[(int)(i % 48)][(int)(horario.dia + (i / 48))] = var.getName(); 
				}
			}
		}
		
		System.out.print("Horários      |");
		for (int dia = 0; dia <6 ; dia++) {
			if(n_caso==3) {
				System.out.print(" ");
			}
			switch(dia) {
			case 0:
				System.out.print("    segunda   ");
				System.out.print(" | ");
				break;
			case 1:
			System.out.print("    terça    ");
			System.out.print(" | ");
			break;
			case 2:
				System.out.print("    quarta   ");
				System.out.print(" | ");
				break;
			case 3:
				System.out.print("    quinta   ");
				System.out.print(" | ");
				break;
			case 4:
				System.out.print("    sexta    ");
				System.out.print(" | ");
				break;
			case 5:
				System.out.print("    sabado   ");
				System.out.print(" | ");
				break;
			}};
			
			System.out.print("\n");
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
