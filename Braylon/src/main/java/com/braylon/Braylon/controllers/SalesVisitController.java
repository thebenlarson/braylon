/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.controllers;

import com.braylon.Braylon.service.SalesVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author DEV10
 */
@Controller
public class SalesVisitController {
    
    private final SalesVisitService salesVisitService;
   
    @Autowired
    public SalesVisitController(SalesVisitService salesVisitService) {
        this.salesVisitService = salesVisitService;
    }
    
}
