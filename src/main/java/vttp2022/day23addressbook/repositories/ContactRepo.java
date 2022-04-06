package vttp2022.day23addressbook.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.model.Contact;

import static vttp2022.day23addressbook.repositories.Queries.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ContactRepo {
    private Logger logger = Logger.getLogger(ContactRepo.class.getName());
    
    @Autowired
    private JdbcTemplate template;

    public boolean setContacts(String email, String name, String passphrase){
        return setContacts(email, name, null, null, null, passphrase);
    }

    public boolean setContacts(Contact contact){
        return setContacts(contact.getEmail(), contact.getName(), 
                contact.getPhone(), contact.getDob(), 
                contact.getStatus(), contact.getPassphrase());
    }

    public boolean setContacts(String email, String name, String phone, String dob, String status, String passphrase){
        int added = template.update(
            SQL_INSERT_CONTACT, email, name, phone, dob, status, passphrase
        );

        return added > 0;
    }

    public boolean deleteContact(String email){
        int deleted = template.update(
            SQL_INSERT_CONTACT, email
        );
        return deleted> 0;
    }

    public Optional<Contact> getContactByEmail(String email){
        final SqlRowSet result = template.queryForRowSet(
            SQL_SELECT_CONTACT_BY_EMAIL, email
        );
        if (!result.next())
            return Optional.empty();

        Contact c = Contact.create(result);
        logger.log(Level.INFO, "Queried email >>>> " + c.getEmail());
        return Optional.of(c);
    } 

    public List<Contact> getContactList(){
        List<Contact> contactList = new ArrayList<>();
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_CONTACTS);
        while (result.next()){
            // contactList.add(getContactByEmail(result.getString("email")).get());
            Contact c = Contact.create(result);
            contactList.add(c);
        }
        return contactList;

    }

}
