package com.example.workshop14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Iterator;

import java.io.File;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.workshop14.model.Contact;
import com.example.workshop14.service.ContactService;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @Autowired
    private ApplicationArguments applicationArguments;

    @Autowired
    ContactService service;

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
        logger.info("contactId > " + contactId);
        Contact cResp = service.findById(contactId);
        model.addAttribute("contact", cResp);
        return "showcontact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        logger.info("Name > " + contact.getName());
        logger.info("Email > " + contact.getEmail());
        logger.info("Phone Number > " + contact.getPhoneNumber());
        String contactId = this.getRandomHexString(8);
        logger.info("contactId > " + contactId);
        Contact persistContact = new Contact(contactId, contact.getName(), 
            contact.getEmail(), 
            contact.getPhoneNumber());
        service.save(persistContact);    
        model.addAttribute("contact", persistContact);
        return "showcontact";
    }

    private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

}
