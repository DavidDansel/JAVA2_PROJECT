package java2.phoneBook.view;
import java2.phoneBook.App;
import java2.phoneBook.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class UpdateSelectedRowController extends AddContactController {
    Person selectedContact;
    private static class MandatoryFieldsException extends Exception{
        public MandatoryFieldsException(){
            super("(lastName, firstName, or nickname) text boxes cannot be empty");
        }
    }
    @FXML
    private void loadContactDetails(){
        selectedContact=UpdateContactController.getSelectedContact();
        lastNameBox.setText(selectedContact.getLastName());
        firstNameBox.setText(selectedContact.getFirstName());
        nickNameBox.setText(selectedContact.getNickName());
        if(selectedContact.getPhoneNumber()==null)
            phoneNumberBox.setText("");
        else
            phoneNumberBox.setText(selectedContact.getPhoneNumber());

        if(selectedContact.getAddress()==null)
            addressBox.setText("");
        else
            addressBox.setText(selectedContact.getAddress());

        if(selectedContact.getEmailAddress()==null)
            emailAddressBox.setText("");
        else
            emailAddressBox.setText(selectedContact.getEmailAddress());

        if(selectedContact.getBirthDate()!=null)
            birthDateBox.setValue(selectedContact.getBirthDate().toLocalDate());
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
            personDao.updateContact(selectedContact.getPersonId(), lastNameBoxText,firstNameBoxText,nickNameBoxText,phoneNumberBoxText,addressBoxText,
                    emailAddressBoxText,birthDateBoxText);
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Record Updated successfully");
            alert.showAndWait();
            try{
                App.setRoot("/java2/phoneBook/view/UpdateContact");
            }catch (IOException e){
                System.out.println("Error: UpdateContact.FXML file or Controller not found");
            }
        }catch(MandatoryFieldsException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }
}
