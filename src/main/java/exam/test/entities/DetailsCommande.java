package exam.test.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false, of = { "article", "quantite", "prixTotal" })
@Entity
@Table(name = "details")
public class DetailsCommande {
    @Column(nullable = false)
    private int quantite;

    @Column(nullable = false)
    private int prixTotal;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    public DetailsCommande(Article article, int quantité) {
        this.article = article;
        this.quantite = quantité;
        this.prixTotal = ((article.getPrixUnitaire()) * quantité);
    }
}
