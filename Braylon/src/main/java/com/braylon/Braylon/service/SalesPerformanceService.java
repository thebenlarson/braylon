/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.service;

import com.braylon.Braylon.adapters.SalesPerformanceAdapter;
import com.braylon.Braylon.entities.CustomerOrder;
import com.braylon.Braylon.entities.Role;
import com.braylon.Braylon.entities.User;
import com.braylon.Braylon.repositories.CustomerOrderRepo;
import com.braylon.Braylon.repositories.SalesVisitRepo;
import com.braylon.Braylon.repositories.UserRepo;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author benth
 */
@Service
public class SalesPerformanceService {
    
    @Autowired
    SalesVisitRepo salesVisitRepo;
    
    @Autowired
    CustomerOrderRepo customerOrderRepo;
    
    @Autowired
    UserRepo userRepo;
    
    public List<SalesPerformanceAdapter> getSalesPerformancesForUsers(List<User> list){
        List<SalesPerformanceAdapter> output = new ArrayList<>();
        for (User user : list){
            output.add(getSalesPerformanceForUser(user));
        }
        return output;
    }
    
    private SalesPerformanceAdapter getSalesPerformanceForUser(User user){
        SalesPerformanceAdapter obj = new SalesPerformanceAdapter();
        List<CustomerOrder> orders = customerOrderRepo.findAllByUser(user, Sort.by(Sort.Direction.ASC, "orderId"));
        
        // counting variables
        int totalSalesCompleted = 0, weekToDate = 0, monthToDate = 0, yearToDate = 0;
        
        //for date calculations
        long weekEpoch = 0, monthEpoch = 0, yearEpoch = 0;
        setEpochs(weekEpoch, monthEpoch, yearEpoch);
        
        //Setting the obj
        obj.setUser(user);
        obj.setTotalSalesVisits(salesVisitRepo.findAllByUser(user).size());
        
        for (CustomerOrder order : orders){
            long epoch = order.getSubmission_date().toEpochSecond(ZoneOffset.UTC);
            if (order.getStatus().getStatus_id() == 6){
                totalSalesCompleted++;
            }
            if (epoch > weekEpoch){
                weekToDate++;
            }
            if (epoch > monthEpoch){
                monthToDate++;
            }
            if (epoch > yearEpoch){
                yearToDate++;
            }
        }
        
        obj.setTotalSalesCompleted(totalSalesCompleted);
        obj.setWeekToDate(weekToDate);
        obj.setMonthToDate(monthToDate);
        obj.setYearToDate(yearToDate);
        
        return obj;
    }
    
    private void setEpochs(long weekEpoch, long monthEpoch, long yearEpoch){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        //week
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        weekEpoch = cal.getTimeInMillis();
        //month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        monthEpoch = cal.getTimeInMillis();
        //year
        cal.set(Calendar.DAY_OF_YEAR, 1);
        yearEpoch = cal.getTimeInMillis();
    }
    
    public List<User> getUserListForLoggedInUser(){
        List<User> output = new ArrayList<>();
        
        //get user, then get roles, then add to list and return
        User user = new User();
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!obj.getClass().equals(String.class)) {
            //found a user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            user = userRepo.findUserByUsername(username);
        } else {
            return output;
        }
        
        boolean getAllUsers = false;
        
        for (Role role : user.getRoles()){
            if (role.getRole_id() == 1){
                getAllUsers = true;
            }
        }
        
        if (getAllUsers){
            output = userRepo.findAll();
        } else {
            output.add(user);
        }
        
        
        
        return output;
    }
}
