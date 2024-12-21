package exam.test.repository.jpaImpl;

import exam.test.core.RepositoryJpaImpl;
import exam.test.entities.Client;
import exam.test.repository.ClientRepository;
import jakarta.persistence.EntityManager;

public class ClientRepositoryJpaImpl extends RepositoryJpaImpl<Client> implements ClientRepository {

    public ClientRepositoryJpaImpl(EntityManager em) {
        super(Client.class, em);
    }

}