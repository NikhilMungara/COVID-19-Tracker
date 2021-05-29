package com.nikhil.COVID19_Tracker.model;

public class LocationStats {

    private String state;
    private String country;
    private int totalCases;
    private int differenceInCasesEveryDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getDifferenceInCasesEveryDay() {
        return differenceInCasesEveryDay;
    }
    public void setDifferenceInCasesEveryDay(int differenceInCasesEveryDay){
        this.differenceInCasesEveryDay = differenceInCasesEveryDay;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCases=" + totalCases +
                '}';
    }
}
