package exam.test.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false, of = { "nom", "prenom", "telephone" })
@Entity
@Table(name = "clients")
public class Client extends AbstractEntity {

    @Column(length = 25, unique = true, nullable = false)
    private String telephone;

    @Column(length = 255, nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "client", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH }, orphanRemoval = true)
    private List<Commande> commandes;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

}
