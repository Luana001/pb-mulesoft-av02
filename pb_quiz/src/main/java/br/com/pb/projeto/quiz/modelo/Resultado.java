package br.com.pb.projeto.quiz.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "RESULTADOS")
public class Resultado {

	@Id
	@GeneratedValue
	private int id;
	private String jogador; 
	private int acertos;
	private int erros;
	@Column (name = "data_da_partida")
	private LocalDate data = LocalDate.now();
	
	public Resultado() {
	}
	
	public Resultado(String jogador) {
		this.jogador = jogador;
	}
	
	public String getJogador() {
		return jogador;
	}
	
	public int getAcertos() {
		return acertos;
	}
	
	public int getErros() {
		return erros;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}
	
	public void setErros(int erros) {
		this.erros = erros;
	}	
	
	public void mostraResultado() {
		System.out.println("\nJogador: " + this.jogador + "\nAcertos: " + this.acertos + "\nErros: " + this.erros);
	}
	
}
