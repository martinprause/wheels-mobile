package com.doit.wheels.controllers;

import com.doit.wheels.services.PrintJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/print-job")
public class PrintJobController {

    private final PrintJobService printJobService;

    @Autowired
    public PrintJobController(PrintJobService printJobService) {
        this.printJobService = printJobService;
    }

    @PostMapping(params = {"orderId", "userId"})
    @ResponseBody
    public ResponseEntity createPrintJob(@RequestParam("orderId") Long orderId,
                                         @RequestParam("userId") Long userId) {
        printJobService.addPrintJob(orderId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
