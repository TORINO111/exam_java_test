package exam.test.services;

import exam.test.repository.CommandeRepository;
import exam.test.entities.Commande;

import java.util.List;

import exam.test.core.Item;

public class CommandeService implements Item<Commande>{
    private CommandeRepository commandeRepository;

public CommandeService(CommandeRepository commandeRepository) {
    this.commandeRepository = commandeRepository;
}

@Override
public void create(Commande element) {
    commandeRepository.insert(element);
}

@Override
public void update(Commande element) {
    commandeRepository.insert(element);
}

@Override
public List<Commande> listAll() {
    return commandeRepository.selectAll();
}
}
