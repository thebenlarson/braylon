/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.braylon.Braylon.adapters;

import com.braylon.Braylon.entities.User;

/**
 *
 * @author benth
 */
public class SalesPerformanceAdapter {
    User user;
    int totalSalesVisits;
    int totalSalesCompleted;
    int weekToDate;
    int monthToDate;
    int yearToDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalSalesVisits() {
        return totalSalesVisits;
    }

    public void setTotalSalesVisits(int totalSalesVisits) {
        this.totalSalesVisits = totalSalesVisits;
    }

    public int getTotalSalesCompleted() {
        return totalSalesCompleted;
    }

    public void setTotalSalesCompleted(int totalSalesCompleted) {
        this.totalSalesCompleted = totalSalesCompleted;
    }

    public int getWeekToDate() {
        return weekToDate;
    }

    public void setWeekToDate(int weekToDate) {
        this.weekToDate = weekToDate;
    }

    public int getMonthToDate() {
        return monthToDate;
    }

    public void setMonthToDate(int monthToDate) {
        this.monthToDate = monthToDate;
    }

    public int getYearToDate() {
        return yearToDate;
    }

    public void setYearToDate(int yearToDate) {
        this.yearToDate = yearToDate;
    }
    
    
}
