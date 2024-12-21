package exam.test.repository.jpaImpl;

import exam.test.core.RepositoryJpaImpl;
import exam.test.entities.DetailsCommande;
import exam.test.repository.DetailsCommandeRepository;
import jakarta.persistence.EntityManager;

public class DetailsCommandeRepositoryJpaImpl extends RepositoryJpaImpl<DetailsCommande> implements DetailsCommandeRepository {

    public DetailsCommandeRepositoryJpaImpl(EntityManager em) {
        super(DetailsCommande.class, em);
    }

}
