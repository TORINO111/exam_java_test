package exam.test.controllers;

import java.util.List;
import java.util.stream.Collectors;

import exam.test.App;
import exam.test.entities.Client;
import exam.test.entities.Article;
import exam.test.entities.Commande;
import exam.test.entities.DetailsCommande;
import exam.test.services.ArticleService;
import exam.test.services.CommandeService;
import exam.test.services.DetailsCommandeService;
import exam.test.services.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class FormAjoutCommandeController {
    private final ArticleService articleService = App.getArticleService();
    private final CommandeService commandeService = App.getCommandeService();
    private final DetailsCommandeService detailsCommandeService = App.getDetailsCommandeService();
    private final ClientService clientService = App.getClientService();
    
    private List<DetailsCommande> panierArticlesChoisis;
    private List<Article> articles;
    private Article selectedArticle;
    private Client selectedClient;

    @FXML private TextField champ_numero;
    @FXML private TextField champ_adresse;
    @FXML private TextField champ_name;
    
    @FXML private ComboBox<Article> combo_article;
    
    @FXML private Spinner<Integer> spinner_quantite;
    @FXML private Spinner<Integer> spinner_prix;
    
    @FXML private Button button_ajouter;
    @FXML private Button button_valider_commande;
    
    @FXML private AnchorPane anchor_choix;
    @FXML private TableView<DetailsCommande> tabArticles;
    @FXML private TableColumn<DetailsCommande, String> col_article;
    @FXML private TableColumn<DetailsCommande, Integer> col_prix;
    @FXML private TableColumn<DetailsCommande, Integer> col_quantite;
    @FXML private TableColumn<DetailsCommande, Integer> col_montant;
    
    @FXML private Label label_montant_total;
    @FXML private Label label_quantite;

    @FXML
    public void initialize() {
        String tel = champ_numero.getText();
        selectedClient = clientService.getClientByTel(tel);
        boolean clientExiste = clientTrouve(selectedClient);


        //Spinners et leurs factories
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0, 1);
        SpinnerValueFactory<Integer> valueFactoryUnitaire = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000, 0, 1);
        spinner_quantite.setValueFactory(valueFactory);
        spinner_prix.setValueFactory(valueFactoryUnitaire);
    
        if (clientExiste) {
            articles = articleService.listAll();
            ObservableList<Article> observableArticles = FXCollections.observableArrayList(articles);
            combo_article.setItems(observableArticles);
    
            combo_article.setCellFactory(
                    (Callback<ListView<Article>, ListCell<Article>>) new Callback<ListView<Article>, ListCell<Article>>() {
                        @Override
                        public ListCell<Article> call(ListView<Article> param) {
                            return new ListCell<>() {
                                @Override
                                protected void updateItem(Article item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (item == null || empty) {
                                        setText(null);
                                    } else {
                                        setText(item.getLibelle());
                                    }
                                }
                            };
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



    }
    private void populateTreeTable() {
        TreeItem<DetailsCommande> rootItem = new TreeItem<>(null);
        rootItem.setExpanded(true);

        for (DetailsCommande article : this.panierArticlesChoisis) {
            TreeItem<DetailsCommande> articleItem = new TreeItem<>(article);
            rootItem.getChildren().add(articleItem);
        }
        
        tabArticles.setRoot(rootItem);
        tabArticles.setShowRoot(false);
    }

    private boolean clientTrouve(){
        if(checkLongueur()) {
            if (client != null) {
                champ_name.setText(client.getPrenom() + " " + client.getNom());
                champ_adresse.setText(client.getAdresse());
    
                anchor_choix.setDisable(false);
                anchor_choix.setOpacity(1);
    
                return true;
            } else {
                return false;
            }
        }
        
    }

    // Fonction appelée par le bouton "Ajouter"
    @FXML
    private void ajoutTableauArticles() {
        Article article = combo_article.getSelectionModel().getSelectedItem();
        this.selectedArticle  = article;
        int quantite = spinner_quantite.getValue();
        int prix = spinner_prix.getValue();

        if (quantite > article.getQteStock()){
            label_quantite.setText("** Uniquement " + article.getQteStock() + " pièce(s) en stock! **");
        } else{
            DetailsCommande detail = new DetailsCommande(article, quantite);
            this.panierArticlesChoisis.add(detail);

        }
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
        
    }
}
