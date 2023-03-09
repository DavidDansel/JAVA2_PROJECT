package java2.phoneBook.view;

import daos.PersonDao;
import java2.phoneBook.App;
import java2.phoneBook.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ContactsListController implements Initializable {

    @FXML
    private TableColumn<Person, String> address;

    @FXML
    private TableColumn<Person, Date> bDate;

    @FXML
    private TableView<Person> contactsListsTable;

    @FXML
    private TableColumn<Person, String> email;

    @FXML
    private TableColumn<Person, String> fName;

    @FXML
    private TableColumn<Person, Integer> id;

    @FXML
    private TableColumn<Person, String> lName;

    @FXML
    private TableColumn<Person, String> nickName;

    @FXML
    private TableColumn<Person, String> phoneNo;

    @FXML
    private void handleHomeButton() throws IOException {
        App.setRoot("/java2/phoneBook/view/HomePage");
    }


    public TableView<Person> getTableView(){
        return contactsListsTable;
    }

    ObservableList<Person> list=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersonDao personDao=new PersonDao();
        List<Person> contacts=personDao.listContacts();
        if(contacts!=null) {
            for (int i = 0; i < contacts.size(); i++)
                list.add(new Person(contacts.get(i).getPersonId(), contacts.get(i).getLastName(), contacts.get(i).getFirstName(), contacts.get(i).getNickName(),
                        contacts.get(i).getPhoneNumber(), contacts.get(i).getAddress(), contacts.get(i).getEmailAddress(),
                        contacts.get(i).getBirthDate()));
            id.setCellValueFactory(new PropertyValueFactory<Person, Integer>("personId"));
            lName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
            fName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
            nickName.setCellValueFactory(new PropertyValueFactory<Person, String>("nickName"));
            email.setCellValueFactory(new PropertyValueFactory<Person, String>("emailAddress"));
            address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
            phoneNo.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
            bDate.setCellValueFactory(new PropertyValueFactory<Person, Date>("birthDate"));
            contactsListsTable.setItems(list);
        }
    }
}


