package game.mozzi;

import game.mozzi.domain.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@EnableJpaAuditing
@SpringBootApplication
public class MozziApplication {

	public static void main(String[] args) {
		SpringApplication.run(MozziApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();


		try{

//			Member member = new Member();
			Member member = Member.builder()
					.socialId("TEST1")
					.build();
//			member.setSocialId("TEST1");
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
