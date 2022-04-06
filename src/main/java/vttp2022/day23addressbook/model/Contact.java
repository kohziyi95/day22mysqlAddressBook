package vttp2022.day23addressbook.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

import jakarta.json.JsonObject;

public class Contact {
    private String email;
    private String name;
    private String phone;
    private String status;
    private String dob;
    private String passphrase;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getPassphrase() {
        return passphrase;
    }
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public static Contact create(MultiValueMap<String,String> body){
        Contact c = new Contact();
        c.setEmail(body.getFirst("email"));
        c.setName(body.getFirst("name"));
        c.setPhone(body.getFirst("phone"));
        c.setStatus(body.getFirst("status"));
        c.setDob(body.getFirst("dob"));
        c.setPassphrase(body.getFirst("passphrase"));
        return c;
    }

    public static Contact create(SqlRowSet result){
        Contact c = new Contact();
        c.setEmail(result.getString("email"));
        c.setName(result.getString("name"));
        c.setPhone(result.getString("phone"));
        c.setStatus(result.getString("status"));
        c.setDob(result.getString("dob"));
        c.setPassphrase(result.getString("pass_phrase"));
        return c;
    }
    
}
