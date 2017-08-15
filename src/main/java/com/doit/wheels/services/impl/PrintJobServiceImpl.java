package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.entities.PrintJob;
import com.doit.wheels.dao.entities.User;
import com.doit.wheels.dao.repositories.GenericRepository;
import com.doit.wheels.services.OrderService;
import com.doit.wheels.services.PrintJobService;
import com.doit.wheels.services.UserService;
import com.doit.wheels.utils.enums.PrintJobStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrintJobServiceImpl extends GenericServiceImpl<PrintJob> implements PrintJobService {

    private final OrderService orderService;

    private final UserService userService;

    @Autowired
    public PrintJobServiceImpl(GenericRepository<PrintJob> genericRepository, OrderService orderService, UserService userService) {
        super(genericRepository);
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public void addPrintJob(Long orderId, Long userId){
        User user = userService.findById(userId);
        Order order = orderService.findById(orderId);
        PrintJob printJob = new PrintJob();
        printJob.setOrder(order);
        printJob.setUser(user);
        printJob.setJobCreated(new Date());
        printJob.setPrintJobStatusEnum(PrintJobStatusEnum.ACTIVE);
        this.save(printJob);
    }
}
