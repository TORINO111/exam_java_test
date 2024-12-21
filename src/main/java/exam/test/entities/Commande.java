package exam.test.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = { "client", "montantTotal"})
@Entity
@Table(name = "dettes")
public class Commande extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "dette", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH }, orphanRemoval = true)
    private List<DetailsCommande> detailsCommandes = new ArrayList<>();

    @Column(name = "montant_total", nullable = true)
    private int montantTotal;


}
