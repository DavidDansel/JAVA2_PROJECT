package java2.phoneBook.view;
import daos.PersonDao;
import java2.phoneBook.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


public class AddContactController {

    private class MandatoryFieldsException extends Exception{
        public MandatoryFieldsException(){
            super("(lastName, firstName, or nickname) text boxes cannot be empty");
        }
    }
    PersonDao personDao=new PersonDao();
    @FXML
    TextField lastNameBox;

    @FXML
    TextField firstNameBox;

    @FXML
    TextField nickNameBox;

    @FXML
    TextField addressBox;

    @FXML
    TextField emailAddressBox;

    @FXML
    TextField phoneNumberBox;

    @FXML
    Button doneBtn;

    @FXML
    Button homeBtn;

    @FXML
    DatePicker birthDateBox;


    public void clearTextBoxes(){
        lastNameBox.clear();
        firstNameBox.clear();
        nickNameBox.clear();
        phoneNumberBox.clear();
        addressBox.clear();
        emailAddressBox.clear();
    }

    @FXML
    private void handleHomeButton() throws IOException {
        App.setRoot("/java2/phoneBook/view/HomePage");
    }

    @FXML
    public void handleDoneButton(){
        try{
            String lastNameBoxText=lastNameBox.getText().strip();
            String firstNameBoxText=firstNameBox.getText().strip();
            String nickNameBoxText=nickNameBox.getText().strip();

            String phoneNumberBoxText=phoneNumberBox.getText().strip();
            if(phoneNumberBoxText.length()==0)
                phoneNumberBoxText=null;

            String addressBoxText=addressBox.getText().strip();
            if(addressBoxText.length()==0)
                addressBoxText=null;

            String emailAddressBoxText=emailAddressBox.getText().strip();
            if(emailAddressBoxText.length()==0)
                emailAddressBoxText=null;

            LocalDate date= birthDateBox.getValue();
            java.sql.Date birthDateBoxText=null;
            if(date!=null)
                birthDateBoxText= Date.valueOf(date);

            if(lastNameBoxText.length()==0||firstNameBoxText.length()==0||nickNameBoxText.length()==0)
                throw new MandatoryFieldsException();
            personDao.addPerson(lastNameBoxText,firstNameBoxText,nickNameBoxText,phoneNumberBoxText,addressBoxText,
                    emailAddressBoxText,birthDateBoxText);
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"new record added");
            alert.showAndWait();
            clearTextBoxes();
        }catch(MandatoryFieldsException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }

    }

}
