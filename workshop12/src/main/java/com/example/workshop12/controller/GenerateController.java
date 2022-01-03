package com.example.workshop12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Iterator;

import com.example.workshop12.exception.RandomNumException;

@Controller
public class GenerateController {
    private static final Logger logger = LoggerFactory.getLogger(GenerateController.class);

    @GetMapping("/generate")
    public String generateForm(Model model) {
        model.addAttribute("generate", new Generate());
        return "generate";
    }

    @PostMapping("/generate")
    public String generateSubmit(@ModelAttribute Generate generate, Model model) {
        logger.info("No of numbers > " + generate.getNumberVal());
        if (generate.getNumberVal() > 12)
            throw new RandomNumException();
        String[] numbersImage = {
                "1.png", "2.png", "3.png", "4.png",
                "5.png", "6.png", "7.png", "8.png",
                "9.png", "10.png", "11.png", "12.png",

        };
        List<String> list = new ArrayList<>();
        Random randNum = new Random();
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < generate.getNumberVal()) {
            Integer x = randNum.nextInt(generate.getNumberVal()) + 1;
            set.add(x);
        }

        Iterator<Integer> it = set.iterator();
        Integer currentElement = null;
        while (it.hasNext()) {
            currentElement = it.next();
            logger.info("currentElement > " + currentElement);
            list.add(numbersImage[currentElement.intValue() - 1]);
        }
        model.addAttribute("numbersResult", list.toArray());

        return "result";
    }

}
