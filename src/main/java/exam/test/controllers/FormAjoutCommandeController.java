package exam.test.controllers;

import java.util.List;

import exam.test.App;
import exam.test.entities.Article;
import exam.test.entities.Client;
import exam.test.entities.Commande;
import exam.test.entities.DetailsCommande;
import exam.test.services.ArticleService;
import exam.test.services.ClientService;
import exam.test.services.CommandeService;
import exam.test.services.DetailsCommandeService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class FormAjoutCommandeController {
    private final ArticleService articleService = App.getArticleService();
    private final CommandeService commandeService = App.getCommandeService();
    private final DetailsCommandeService detailsCommandeService = App.getDetailsCommandeService();
    private final ClientService clientService = App.getClientService();

    private List<DetailsCommande> panierArticlesChoisis;
    private List<Article> articles;
    private Client selectedClient;

    private int total = detailsCommandeService.montantPanier(this.panierArticlesChoisis);

    @FXML
    private TextField champ_numero;
    @FXML
    private TextField champ_adresse;
    @FXML
    private TextField champ_name;

    @FXML
    private ComboBox<Article> combo_article;

    @FXML
    private Spinner<Integer> spinner_quantite;
    @FXML
    private TextField spinner_prix;

    @FXML
    private Button button_ajouter;
    @FXML
    private Button button_valider_commande;

    @FXML
    private AnchorPane anchor_choix;
    @FXML
    private TableView<DetailsCommande> tabArticles;
    @FXML
    private TableColumn<DetailsCommande, String> col_article;
    @FXML
    private TableColumn<DetailsCommande, Integer> col_prix;
    @FXML
    private TableColumn<DetailsCommande, Integer> col_quantite;
    @FXML
    private TableColumn<DetailsCommande, Integer> col_montant;

    @FXML
    private Label label_montant_total;
    @FXML
    private Label label_quantite;
    @FXML
    private Label label_numero;

    @FXML
    public void initialize() {
        setupSpinner();
        setupComboBox();
        setupTableColumns();
        setupButtonActions();

        if (this.panierArticlesChoisis != null) {
            populateTable();
        }
    }

    private void setupSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0,
                1);
        spinner_quantite.setValueFactory(valueFactory);
    }

    private void setupComboBox() {
        this.articles = articleService.listAll();
        ObservableList<Article> observableArticles = FXCollections.observableArrayList(articles);
        combo_article.setItems(observableArticles);

        combo_article.setCellFactory((Callback<ListView<Article>, ListCell<Article>>) param -> new ListCell<>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getLibelle());
                }
            }
        });

        combo_article.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getLibelle());
                }
            }
        });
    }

    private void setupTableColumns() {
        col_article
                .setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getArticle().getLibelle()));
        col_prix.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue().getArticle().getPrixUnitaire()));
        col_quantite.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getQuantite()));
        col_montant.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getPrixTotal()));

        col_quantite.setOnEditCommit(event -> {
            DetailsCommande details = event.getRowValue();
            int nouvelleQuantite = event.getNewValue();

            if (nouvelleQuantite > details.getArticle().getQteStock()) {
                label_quantite.setText(
                        "Stock insuffisant : seulement " + details.getArticle().getQteStock() + " disponibles.");
                tabArticles.refresh();
            } else {
                details.setQuantite(nouvelleQuantite);
                populateTable();
            }
        });
    }

    private void setupButtonActions() {
        champ_numero.setOnKeyReleased(event -> manageClient());
        button_ajouter.setOnAction(event -> ajoutTableauArticles());
        button_valider_commande.setOnAction(event -> validerCommande());
    }

    private void populateTable() {
        ObservableList<DetailsCommande> data = FXCollections.observableArrayList(this.panierArticlesChoisis);
        tabArticles.setItems(data);
    }

    private void manageClient() {
        if (checkLongueur()) {
            String tel = champ_numero.getText();
            Client client = clientService.getClientByTel(tel);
            this.selectedClient = client;

            if (client != null) {
                champ_name.setText(client.getPrenom() + " " + client.getNom());
                champ_adresse.setText(client.getAdresse());

                anchor_choix.setDisable(false);
                anchor_choix.setOpacity(1);

            } else {
                label_numero.setText("Aucun client trouvé!");
            }
        }

    }

    @FXML
    private void ajoutTableauArticles() {
        Article article = combo_article.getSelectionModel().getSelectedItem();
        int quantite = spinner_quantite.getValue();
        if (article != null && quantite > 0) {
            if (quantite > article.getQteStock()) {
                label_quantite.setText("** Uniquement " + article.getQteStock() + " pièce(s) en stock! **");
            } else {
                DetailsCommande detail = new DetailsCommande(article, quantite);
                this.panierArticlesChoisis.add(detail);
                tabArticles.refresh();
            }
        } else {
            label_quantite.setText("Veuillez choisir un article et une quantité!");
        }

    }

    @FXML
    private void remplirChampPrix() {
        Article article = combo_article.getSelectionModel().getSelectedItem();
        spinner_prix.setText(String.valueOf(article.getPrixUnitaire()));
    }

    @FXML
    private boolean checkLongueur() {
        String text = champ_numero.getText();
        if (text.length() == 9) {
            return true;
        } else {
            return false;
        }
    }

    // Fonction appelée par le bouton "Valider la commande"
    @FXML
    private void validerCommande() {
        if (this.selectedClient == null) {
            label_numero.setText("Aucun client sélectionné.");
            return;
        }

        if (this.panierArticlesChoisis.isEmpty()) {
            label_quantite.setText("Le panier est vide.");
            return;
        }

        // Création de la commande
        Commande commande = commandeService.defarCommande(this.selectedClient, this.panierArticlesChoisis);

        this.selectedClient.getCommandes().add(commande);

        for (DetailsCommande detailsCommande : this.panierArticlesChoisis) {
            articleService.setDetailsCommande(detailsCommande);
            detailsCommande.setCommande(commande);
        }

        commandeService.create(commande);
        clientService.update(this.selectedClient);

        for (DetailsCommande detailsCommande : this.panierArticlesChoisis) {
            detailsCommandeService.update(detailsCommande);
        }
        // Réinitialiser les champs
        this.panierArticlesChoisis.clear();
        populateTable();
        label_quantite.setText("Commande validée avec succès !");
    }

}
