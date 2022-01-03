package com.example.workshop13.controller;

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

import java.io.FileWriter;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.workshop13.model.Contact;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @Autowired
    private ApplicationArguments applicationArguments;

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
        logger.info("contactId > " + contactId);

        Set<String> optnames = applicationArguments.getOptionNames();
        logger.info("optnames > " + optnames);
        String[] optnamesArr = optnames.toArray(new String[optnames.size()]);
        List<String> optValues = applicationArguments.getOptionValues(optnamesArr[0]);
        String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
        logger.info("values > " + optValuesArr[0]);
        Contact cResp = new Contact();
        try {
            Path filePath = new File(optValuesArr[0]+ '/' + contactId).toPath();
            Charset charset = Charset.forName("utf-8");        
            List<String> stringList = Files.readAllLines(filePath, charset);
            String[] stringArray = stringList.toArray(new String[]{});
            logger.info("stringArray > " + stringArray.length);
            logger.info("stringArray > " + stringArray);
            cResp.setName(stringArray[0]);
            cResp.setEmail(stringArray[1]);
            cResp.setPhoneNumber(Integer.parseInt(stringArray[2]));
            
        }catch(IOException e){
            logger.error(e.getMessage());
        }
        
        model.addAttribute("contact", cResp);
        return "showcontact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        logger.info("Name > " + contact.getName());
        logger.info("Email > " + contact.getEmail());
        logger.info("Phone Number > " + contact.getPhoneNumber());
        String dataFilename = this.getRandomHexString(8);
        logger.info("hex > " + dataFilename);
        Set<String> optnames = applicationArguments.getOptionNames();
        logger.info("optnames > " + optnames);
        String[] optnamesArr = optnames.toArray(new String[optnames.size()]);
        List<String> optValues = applicationArguments.getOptionValues(optnamesArr[0]);
        String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
        logger.info("values > " + optValuesArr[0]);
        PrintWriter printWriter = null;
        try{
            FileWriter fileWriter = 
                new FileWriter(optValuesArr[0] +"/"+dataFilename, Charset.forName("utf-8"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());
        }catch(IOException e){
            logger.error(e.getMessage());
        }finally {
            printWriter.close();
        }
            
        model.addAttribute("contact", new Contact(contact.getName(), 
            contact.getEmail(), 
            contact.getPhoneNumber()));
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
