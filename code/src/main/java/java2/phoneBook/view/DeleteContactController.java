package java2.phoneBook.view;
import daos.PersonDao;
import java2.phoneBook.App;
import java2.phoneBook.model.Person;
import javafx.fxml.FXML;

import java.io.IOException;


public class DeleteContactController extends ContactsListController {
    PersonDao personDao=new PersonDao();
    @FXML
    private void handleHomeButton() throws IOException {
        App.setRoot("/java2/phoneBook/view/HomePage");
    }

    @FXML
    private void delSelectedRow(){
       Person selectedRow=getTableView().getSelectionModel().getSelectedItem();
       if(selectedRow!=null){
           getTableView().getItems().removeAll(selectedRow);
           personDao.deletePerson(selectedRow.getPersonId());
       }
    }

}
