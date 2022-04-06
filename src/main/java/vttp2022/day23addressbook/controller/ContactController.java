package vttp2022.day23addressbook.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.day23addressbook.repositories.ContactRepo;
import vttp2022.model.Contact;

@Controller
@RequestMapping("/")
public class ContactController {
    private Logger logger = Logger.getLogger(ContactController.class.getName());
    @Autowired
    private ContactRepo repo;

    @GetMapping("/")
    public String indexForm(){
        return "contactForm";
    }

    @PostMapping(path = "/contact", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String showForm(Model model, @RequestBody MultiValueMap<String,String> payload){
        // JsonObject body;
        // try (InputStream is = new ByteArrayInputStream(payload.getBytes())){
        //     JsonReader reader = Json.createReader(is);
        //     body = reader.readObject();
        // } catch (Exception ex) {
        //     // ex.printStackTrace();
        //     logger.log(Level.WARNING, "Failed to read payload");
        //     return null;
        // }

        Contact c = Contact.create(payload);
        logger.log(Level.INFO, "Contact created >>> " + c.toString());
        if (repo.getContactByEmail(c.getEmail()).isEmpty()){
            if(repo.setContacts(c)){
                logger.log(Level.INFO, "Contact added to database");
            } else {
                logger.log(Level.INFO, "Contact already exists in database");  
            }
        }

        List<Contact> contactList = repo.getContactList();
        logger.log(Level.INFO, "Contactlist created >>> " + contactList.toString());

        model.addAttribute(contactList);
        return "resultForm";
    }
}
