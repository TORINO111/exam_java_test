package exam.test;

import java.io.IOException;
import java.net.URL;

import exam.test.repository.ArticleRepository;
import exam.test.repository.ClientRepository;
import exam.test.repository.CommandeRepository;
import exam.test.repository.DetailsCommandeRepository;
import exam.test.repository.jpaImpl.ArticleRepositoryJpaImpl;
import exam.test.repository.jpaImpl.ClientRepositoryJpaImpl;
import exam.test.repository.jpaImpl.CommandeRepositoryJpaImpl;
import exam.test.repository.jpaImpl.DetailsCommandeRepositoryJpaImpl;
import exam.test.services.ArticleService;
import exam.test.services.ClientService;
import exam.test.services.CommandeService;
import exam.test.services.DetailsCommandeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    // Repositories
    private static CommandeRepository commandeRepository;
    private static ClientRepository clientRepository;
    private static ArticleRepository articleRepository;
    private static DetailsCommandeRepository detailsCommandeRepository;

    // Services
    private static CommandeService commandeService;
    private static DetailsCommandeService detailsCommandeService;
    private static ClientService clientService;
    private static ArticleService articleService;

    private static Scene scene;
    private static Stage stage;

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        initRepositoryAndServices();
        scene = new Scene(loadFXML("FormAjoutCommande"), 1280, 720);

        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL url = App.class.getResource("/views/" + fxml + ".fxml");
        if (url == null) {
            throw new IllegalStateException("Fichier FXML introuvable: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public void initRepositoryAndServices() {
        try {
            emf = Persistence.createEntityManagerFactory("JPAPOSTGRES");
            em = emf.createEntityManager();

            System.out.println("Connexion établie avec succès !");
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPOSTGRES");
        // EntityManager em = emf.createEntityManager();

        articleRepository = new ArticleRepositoryJpaImpl(em);
        commandeRepository = new CommandeRepositoryJpaImpl(em);
        clientRepository = new ClientRepositoryJpaImpl(em);
        detailsCommandeRepository = new DetailsCommandeRepositoryJpaImpl(em);

        articleService = new ArticleService(articleRepository);
        commandeService = new CommandeService(commandeRepository);
        clientService = new ClientService(clientRepository);
        detailsCommandeService = new DetailsCommandeService(detailsCommandeRepository);

    }

    public static ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public static CommandeRepository getCommandeRepository() {
        return commandeRepository;
    }

    public static ClientRepository getClientRepository() {
        return clientRepository;
    }

    public static DetailsCommandeRepository getDetailsCommandeRepository() {
        return detailsCommandeRepository;
    }

    public static ArticleService getArticleService() {
        return articleService;
    }

    public static CommandeService getCommandeService() {
        return commandeService;
    }

    public static ClientService getClientService() {
        return clientService;
    }

    public static DetailsCommandeService getDetailsCommandeService() {
        return detailsCommandeService;
    }
}