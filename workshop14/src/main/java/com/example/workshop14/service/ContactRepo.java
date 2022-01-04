package com.example.workshop14.service;

import java.util.Map;
import com.example.workshop14.model.Contact;

public interface ContactRepo {
    public void save(final Contact ctc);
    public Contact findById(final String id);
    public Map<String, Contact> findAll();
    public void delete(String id);
}
