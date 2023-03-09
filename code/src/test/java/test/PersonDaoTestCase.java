package test;

import daos.DataSourceFactory;
import daos.PersonDao;


import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import java2.phoneBook.model.Person;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PersonDaoTestCase {
    private final PersonDao personDao = new PersonDao();

    @Before
    public void initDatabase() throws Exception {
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        // this will reset idperson autoIncrement to 0
        // hence the first row to be inserted will always has a value of 1
        stmt.executeUpdate("DROP TABLE Person");
        stmt.executeUpdate(

                "CREATE TABLE IF NOT EXISTS Person (\n" +
                        "    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "    lastname VARCHAR(45) NOT NULL,\n" +
                        "    firstname VARCHAR(45) NOT NULL,\n" +
                        "    nickname VARCHAR(45) NOT NULL,\n" +
                        "    phone_number VARCHAR(15) NULL,\n" +
                        "    address VARCHAR(200) NULL,\n" +
                        "    email_address VARCHAR(150) NULL,\n" +
                        "    birth_date DATE NULL);");
        stmt.executeUpdate("DELETE FROM Person");
        stmt.executeUpdate("INSERT INTO Person(lastname,firstname,nickname) " +
                "VALUES ('David','Dansel','YS')");
        stmt.executeUpdate("INSERT INTO Person(lastname,firstname,nickname) " +
                "VALUES ('Xin','Chen','YS')");
        stmt.executeUpdate("INSERT INTO Person(lastname,firstname,nickname) " +
                "VALUES ('Abir','Taha','YS')");

        stmt.close();
        connection.close();
    }


    @Test
    public void shouldListContacts() {

       List<Person> contacts=personDao.listContacts();

       assertThat(contacts).hasSize(3);

       assertThat(contacts).extracting(Person::getLastName).contains("David","Xin","Abir");
    }

    @Test
    public void shouldDeleteContact(){
        personDao.deletePerson(1);
        List<Person> contacts=personDao.listContacts();
        assertThat(contacts).hasSize(2);
    }

    @Test
    public void shouldAddContact(){
        personDao.addPerson("my-test-case","record","added",null,null
        ,null,null);
        List<Person> contacts=personDao.listContacts();
        assertThat(contacts).extracting(Person::getLastName).contains("David","Xin","Abir","my-test-case");
        assertThat(contacts).hasSize(4);
    }

    @Test
    public void shouldUpdateContact(){
        personDao.updateContact(1,"OBO","David","Nig",null,null,
                null,null);
        List<Person> contacts=personDao.listContacts();
        assertThat(contacts).extracting(Person::getLastName).contains("OBO","Xin","Abir");
    }

}
