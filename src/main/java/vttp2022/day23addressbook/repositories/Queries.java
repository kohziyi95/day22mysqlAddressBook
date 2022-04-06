package vttp2022.day23addressbook.repositories;

public class Queries {
    public static final String SQL_INSERT_CONTACT = 
        "insert into bff (email, name, phone, dob, status, pass_phrase) values (?, ?, ?, ?, ?, sha1(?))";
    
    public static final String SQL_SELECT_CONTACTS = "select * from bff";

    public static final String SQL_SELECT_CONTACT_BY_EMAIL = "select * from bff where email like ?";

    public static final String SQL_DELETE_CONTACT_BY_EMAIL = "delete * from bff where email like ?";

}
