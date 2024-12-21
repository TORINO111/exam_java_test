package exam.test.services;

import java.util.List;

import exam.test.core.Item;
import exam.test.entities.DetailsCommande;
import exam.test.repository.DetailsCommandeRepository;

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

    public int montantPanier(List<DetailsCommande> panier) {
        int total = 0;
        for (DetailsCommande detailsCommande : panier) {
            total += detailsCommande.getPrixTotal();
        }
        return total;
    }
}