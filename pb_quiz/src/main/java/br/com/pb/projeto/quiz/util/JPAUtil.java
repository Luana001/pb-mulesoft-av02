package br.com.pb.projeto.quiz.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = 
			Persistence.createEntityManagerFactory("pb_quiz");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
