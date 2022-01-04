package com.example.workshop14.service;
 
import java.util.Map;
 
import javax.annotation.PostConstruct;
 
import com.example.workshop14.service.ContactRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.workshop14.model.Contact;

@Service
public class ContactService implements ContactRepo {
 
    private final String CONTACT_CACHE = "CONTACT";
 
    @Autowired
    RedisTemplate<String, Contact> redisTemplate;
    
 
    // Save operation.
    @Override
    public void save(final Contact ctc) {
        redisTemplate.opsForValue().set(ctc.getId(), ctc);
    }
 
    @Override
    public Contact findById(final String id) {
        return (Contact) redisTemplate.opsForValue().get(id);
    }
 
    @Override
    public Map<String, Contact> findAll() {
        return redisTemplate.opsForValue().entries(CONTACT_CACHE);
    }
 
    @Override
    public void delete(String id) {
        redisTemplate.opsForValue().delete(CONTACT_CACHE, id);
    }
}