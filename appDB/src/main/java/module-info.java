module org.app.db {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.app.db to javafx.fxml;
    exports org.app.db;
}
