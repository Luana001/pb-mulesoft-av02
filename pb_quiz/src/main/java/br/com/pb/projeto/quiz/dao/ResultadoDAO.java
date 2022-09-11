package br.com.pb.projeto.quiz.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.pb.projeto.quiz.modelo.Resultado;

public class ResultadoDAO {

	private EntityManager em;
	
	public ResultadoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void salvarResultados(Resultado resultado) {
		this.em.persist(resultado);
	}
	
	public void mostraPlacar()  {
		String jpql = "SELECT r FROM Resultado r ORDER BY r.acertos DESC";
		
		List<Resultado> placar = em.createQuery(jpql, Resultado.class).setMaxResults(5).getResultList();
		
		for (int i = 0; i < placar.size(); i++) {
			Resultado resultado = placar.get(i);
			System.out.println("Jogador: " + resultado.getJogador() + "   Acertos: " + resultado.getAcertos() + "   Data: " + resultado.getData());
        }
	}

}
