package game.mozzi;

import game.mozzi.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
class MozziApplicationTests {

	@Test
	void contextLoads() {
	}



	// JPA TEST
	@Test
	void JPA() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();


		try{

			Member member = new Member();
			member.setSocialId("TEST1");
			em.persist(member);

			System.out.println(" ================= ");

			member.getNickname();


		}catch (Exception e){
			tx.rollback();
		}finally {
			em.close();
		}

		emf.close();
	}

}
