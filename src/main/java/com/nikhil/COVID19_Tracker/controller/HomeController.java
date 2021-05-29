package com.nikhil.COVID19_Tracker.controller;

import com.nikhil.COVID19_Tracker.model.LocationStats;
import com.nikhil.COVID19_Tracker.service.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ServiceData data;

    /**
     *  To fetch the COVID Data
     * @param model
     * @return
     */
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = data.getStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("Statistics", data.getStats());
        return "home";
    }


}
