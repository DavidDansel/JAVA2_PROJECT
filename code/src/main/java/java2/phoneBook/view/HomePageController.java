package java2.phoneBook.view;
import daos.PersonDao;
import java2.phoneBook.App;
import java2.phoneBook.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HomePageController {
    PersonDao personDao=new PersonDao();
    @FXML
    Button listContactsBtn;
    @FXML
    Button addContactBtn;
    @FXML
    Button updateContactBtn;
    @FXML
    Button deleteContactBtn;
    @FXML
    public void handleListContactsBtn() throws IOException{
          App.setRoot("/java2/phoneBook/view/ContactsList");

    }

    @FXML
    public void handleAddContactBtn() throws IOException{
            App.setRoot("/java2/phoneBook/view/AddContact");
    }

    @FXML
    public void handleUpdateContactBtn() throws IOException{
        App.setRoot("/java2/phoneBook/view/UpdateContact");

    }

    @FXML
    public void handleDeleteContactBtn() throws  IOException{
        App.setRoot("/java2/phoneBook/view/DeleteContact");

    }

    @FXML
    public void handleExportButton() throws IOException {
        List<Person> contacts = personDao.listContacts();
        if (contacts != null) {
            List<String> lines = new ArrayList<>();
            for (Person person : contacts)
                lines.add(person.toString());
            Path path = Paths.get("backUpFile");
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Contacts exported to 'backUpFile'");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "PhoneBook is empty");
            alert.showAndWait();
        }
    }
}
