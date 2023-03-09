module java2.phoneBook {
    requires javafx.controls;
    requires javafx.fxml;
    requires sqlite.jdbc;
    requires java.sql;

    opens java2.phoneBook to javafx.fxml;
    // All our views will be in this package, that must be accessible by JavaFX
    opens java2.phoneBook.view to javafx.fxml;
    opens java2.phoneBook.model to javafx.base;
    exports java2.phoneBook;
}