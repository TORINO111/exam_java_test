package exam.test.repository.jpaImpl;

import exam.test.core.RepositoryJpaImpl;
import exam.test.entities.Commande;
import exam.test.repository.CommandeRepository;
import jakarta.persistence.EntityManager;

public class CommandeRepositoryJpaImpl extends RepositoryJpaImpl<Commande> implements CommandeRepository {

    public CommandeRepositoryJpaImpl(EntityManager em) {
        super(Commande.class, em);
    }

}
