module exam.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.yaml.snakeyaml;
    requires org.postgresql.jdbc;
    requires lombok;

    opens exam.test to javafx.fxml, jakarta.persistence, org.hibernate.orm.core, org.hibernate.orm.jpamodelgen;
    opens exam.test.controllers to javafx.fxml, jakarta.persistence, org.hibernate.orm.core, org.hibernate.orm.jpamodelgen, javafx.controls;
    exports exam.test.controllers to javafx.fxml, javafx.graphics;
    exports exam.test to javafx.fxml, javafx.graphics;
}
