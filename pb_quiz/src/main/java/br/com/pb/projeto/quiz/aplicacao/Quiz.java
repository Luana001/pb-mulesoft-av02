package br.com.pb.projeto.quiz.aplicacao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.pb.projeto.quiz.dao.QuestaoDAO;
import br.com.pb.projeto.quiz.dao.ResultadoDAO;
import br.com.pb.projeto.quiz.modelo.Questao;
import br.com.pb.projeto.quiz.modelo.Resultado;
import br.com.pb.projeto.quiz.util.JPAUtil;

public class Quiz {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		EntityManager em = JPAUtil.getEntityManager();
		int sair = 0, escolha;
		
		System.out.println("Bem vindo ao jogo de perguntas e respostas da nossa empresa!");
		System.out.println("Responda true para verdadeiro e false para falso.");
		jogar(em);
		
		for( ; sair==0 ; ) {
			System.out.println("\n1 - Jogar novamente \n2 - Ver placar\n0 - Sair");
			boolean continua = true;
		    do {
				try {
					escolha = teclado.nextInt();
				    em = JPAUtil.getEntityManager();
					switch (escolha) {
						case 1: jogar(em); 
							continua = false;
							break;
						case 2: System.out.println(); 
							ResultadoDAO acessaResultados = new ResultadoDAO(em);
							acessaResultados.mostraPlacar();
							continua = false;
							break;
						case 0: sair = 1;
							continua = false;
							break;
						
						default: System.out.println("Valor invalido, tente 1, 2 ou 0");
						teclado.nextLine();
					}
				} catch (InputMismatchException e) {
					System.out.println("Escolha invalida, tente numeros entre 0 e 2");
					teclado.nextLine();
				} 	
			} while(continua);
		}
	}

	
	public static void jogar(EntityManager em) {
		Scanner teclado = new Scanner(System.in);
	    int acertos = 0, erros = 0;
	    
	    QuestaoDAO acessaQuestoes = new QuestaoDAO(em);
	    List<Questao> questoes = acessaQuestoes.pegaQuestoes();  
	    
	    System.out.println("\nQual é o seu nome?");
	    Resultado resultado = new Resultado(teclado.next());
	    System.out.println("Ok " + resultado.getJogador() + ", vamos comecar!");
    
	    
	    for(int i=0; i<questoes.size(); i++){
	    	Questao questao = questoes.get(i);
	    	System.out.println("\n" + questao.getPergunta());
	    	boolean resposta, continua = true;
	    	
	    	do {
		        try {
		        	resposta = teclado.nextBoolean();
		        	if(resposta == (questao.isVerdadeira())){
		        		System.out.println("Parabéns, voce acertou!");
		 	            acertos++;
		 	        } else {
		 	        	System.out.println("Sinto muito voce errou.");
		 	            erros++;
		 	        }
		        	continua = false;
		        } catch (InputMismatchException e) {
		        	System.out.println("Resposta invalida, tente true ou false");
		        	teclado.nextLine();
		        }
	    	}while(continua);
	    }
	        
	    resultado.setAcertos(acertos);
	    resultado.setErros(erros);
	    resultado.mostraResultado();

	    ResultadoDAO acessaResultados = new ResultadoDAO(em);
	    em.getTransaction().begin();
	    acessaResultados.salvarResultados(resultado);
		em.getTransaction().commit();
		em.close();

	}
	
}