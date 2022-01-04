package com.example.workshop14;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.workshop14.model.Contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class Workshop14ApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(Workshop14ApplicationTests.class);
	@Test
	void contextLoads() {
	}


	@Test
	public void testContact() throws Exception {
		Contact c = new Contact();
		c.setName("Kenneth");
		c.setEmail("a@a.com");
		c.setPhoneNumber(1234567);
        assertEquals(c.getName(), "Kenneth");
		assertEquals(c.getEmail(), "a@a.com");
		assertEquals(c.getPhoneNumber(), 1234567);
	}

}
