package java2.phoneBook.view;
import java2.phoneBook.App;
import java2.phoneBook.model.Person;
import javafx.fxml.FXML;

import java.io.IOException;

public class UpdateContactController  extends ContactsListController {

    @FXML
    private void handleHomeButton() throws IOException {
        App.setRoot("/java2/phoneBook/view/HomePage");
    }
    private static Person selectedContact=null;

    @FXML
    private void updateSelectedRow() throws IOException {
        selectedContact=getTableView().getSelectionModel().getSelectedItem();
        if(selectedContact!=null)
            App.setRoot("/java2/phoneBook/view/UpdateSelectedRow");
    }

    public static Person getSelectedContact(){
        return  selectedContact;
    }

}
