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
@EqualsAndHashCode(callSuper = false, of = {"libelle"})
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity {

    @Column(length = 55, unique = true, nullable = false)
    private String libelle;

    @Column(nullable = false)
    private int qteStock;

    @Column(nullable = false)
    private int prixUnitaire;

    @OneToMany(mappedBy = "article", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = false)
    private List<DetailsCommande> detailsCommandes;

}
