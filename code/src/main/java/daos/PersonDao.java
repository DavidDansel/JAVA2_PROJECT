package daos;

import java2.phoneBook.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {


    public void addPerson(String lastName, String firstName, String nickName,String phoneNumber,
                          String address,String emailAddress,java.sql.Date birthDate){
        String sqlQuery = "INSERT INTO Person(lastname,firstname,nickname,phone_number,address," +
                "email_address,birth_date)" +
                " VALUES(?,?,?,?,?,?,?)";

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1,lastName);
            statement.setString(2,firstName);
            statement.setString(3,nickName);

            if(phoneNumber==null)
                statement.setNull(4,Types.VARCHAR);
            else
                statement.setString(4,phoneNumber);

            if(address==null)
                statement.setNull(5,Types.VARCHAR);
            else
                statement.setString(5,address);

            if(emailAddress==null)
                statement.setNull(6,Types.VARCHAR);
            else
                statement.setString(6,emailAddress);

            statement.setDate(7,birthDate);

            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("An exception occurred when adding to Person Database");
            }
        } catch (SQLException e) {
            System.out.println("An exception occurred when connecting to database");
        }
    }


    public void deletePerson(int idPerson){
        String sqlQuery = "DELETE FROM Person WHERE idperson=?";
        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1,idPerson);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("An exception occurred when deleting from Person Database");
            }
        } catch (SQLException e) {
            System.out.println("An exception occurred when connecting to database");
        }
    }


    public void updateContact(int id,String lastName,String firstName,String nickName,String phoneNumber,
                              String address,String emailAddress,java.sql.Date birthDate){
        String sqlQuery = "UPDATE Person SET lastname=?,firstname=?,nickname=?, phone_number=?,address=?,email_address=?,birth_date=? WHERE idperson=?";
        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1,lastName);
            statement.setString(2,firstName);
            statement.setString(3,nickName);
            statement.setString(4,phoneNumber);
            statement.setString(5,address);
            statement.setString(6,emailAddress);
            statement.setDate(7,birthDate);
            statement.setInt(8,id);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("An exception occurred when updating record");
            }
        } catch (SQLException e) {
            System.out.println("An exception occurred when connecting to database");
        }
    }

    public List<Person> listContacts(){
        List<Person> contacts=new ArrayList<>();
        String sqlQuery="SELECT * FROM Person";
        int numberOfRows=0;
        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    numberOfRows++;
                    Integer personId=resultSet.getInt("idperson");
                    String lastName= resultSet.getString("lastname");
                    String firstName=resultSet.getString("firstname");
                    String nickName=resultSet.getString("nickname");
                    String phoneNumber=resultSet.getString("phone_number");
                    String address=resultSet.getString("address");
                    String emailAddress=resultSet.getString("email_address");
                    java.sql.Date birthDate=resultSet.getDate("birth_date");
                    contacts.add(new Person(personId,lastName,firstName,nickName,phoneNumber,
                            address,emailAddress,birthDate));
                }
            } catch (SQLException e) {
                System.out.println("An exception occurred generating resultSet");
            }
        } catch (SQLException e) {
            System.out.println("An exception occurred when connection to database");
        }
        if(numberOfRows==0)
            return null;
        return contacts;
    }
}
