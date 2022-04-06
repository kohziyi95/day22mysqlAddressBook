package vttp2022.day23addressbook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.day23addressbook.repositories.ContactRepo;
import vttp2022.model.Contact;

@SpringBootTest
class Day23addressbookApplicationTests {
	@Autowired
	private ContactRepo repo;

	
	
	@Test
	void getContactByEmailShouldBePresent() {
		Optional<Contact> contact = repo.getContactByEmail("test1@test.com");
		assertTrue(contact.isPresent());
	}

	@Test
	void getContactByEmailShouldBeEmpty() {
		Optional<Contact> contact = repo.getContactByEmail("test50@test.com");
		assertTrue(contact.isEmpty());
	}

}
