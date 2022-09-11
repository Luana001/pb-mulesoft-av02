package br.com.pb.projeto.quiz.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "QUESTOES")
public class Questao {

	@Id
	@GeneratedValue
	private int id;
	private String pergunta;
	private boolean verdadeira;
	private boolean ativa;

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	public void setVerdadeira(boolean verdadeira) {
		this.verdadeira = verdadeira;
	}
	
	public String getPergunta() {
		return pergunta;
	}
		
	public boolean isVerdadeira() {
		return verdadeira;
	}
	
	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

}
