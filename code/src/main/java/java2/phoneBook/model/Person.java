package java2.phoneBook.model;

import java.util.Date;

public class Person {

  private Integer personId;
  private String lastName;
  private String firstName;
  private String nickName;
  private String phoneNumber;
  private String address;
  private String emailAddress;
  private java.sql.Date birthDate;


    public Person(){

    }
    public Person(Integer personId,String lastName, String firstName, String nickName,String phoneNumber,
                  String address,String emailAddress,java.sql.Date birthDate){
        this.personId=personId;
       this.lastName=lastName;
       this.firstName=firstName;
       this.nickName=nickName;
       this.phoneNumber=phoneNumber;
       this.address=address;
       this.emailAddress=emailAddress;
       this.birthDate=birthDate;
   }

   public Integer getPersonId(){
        return personId;
   }

    @Override
    public String toString() {

        return "'"+getLastName()+"'"+" " + "'"+getFirstName()+"'"+" "+ "'"+getNickName()+"'"
                +"'"+getPhoneNumber()+"'"+"'"+getAddress()+"'"+"'"+getEmailAddress()+"'"+"'"+getBirthDate()+"'";
    }



    public String getPhoneNumber() {

        return phoneNumber;
    }



    public String getNickName() {

        return nickName;
   }



    public String getEmailAddress() {
        return emailAddress;
    }



    public String getAddress() {

        return address;
    }



    public String getFirstName() {

        return firstName;
    }



    public String getLastName() {
        return lastName;
    }


    public java.sql.Date getBirthDate() {
        return birthDate;
    }

}
