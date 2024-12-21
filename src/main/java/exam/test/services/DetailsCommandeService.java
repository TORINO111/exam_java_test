package exam.test.services;

import exam.test.repository.DetailsCommandeRepository;
import exam.test.entities.DetailsCommande;

import java.util.List;

import exam.test.core.Item;

public class DetailsCommandeService implements Item<DetailsCommande> {

    private DetailsCommandeRepository detailsCommandeRepository;

    public DetailsCommandeService(DetailsCommandeRepository detailsCommandeRepository) {
        this.detailsCommandeRepository = detailsCommandeRepository;
    }

    @Override
    public void create(DetailsCommande element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void update(DetailsCommande element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<DetailsCommande> listAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }
    
}