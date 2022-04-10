package com.service.monitor.app.controller;

import com.service.monitor.app.service.ActivityAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MVCController {

    @Autowired
    private ActivityAuditService activityAuditService;

    @GetMapping("/week_activity")
    public String weekUsers(Model model) {
        model.addAttribute("users",activityAuditService.getUsersActiveInRecentWeek());
        return "usersview";
    }

    @GetMapping("/all_activity")
    public String allUsers(Model model) {
        model.addAttribute("users",activityAuditService.getAllUsers());
        return "usersview";
    }

    @GetMapping("/action_list/{id}")
    public String userDetails(@PathVariable int id, Model model){
        model.addAttribute("actions",activityAuditService.getActionsByUserId(id));
        return "actions";
    }

    @GetMapping("contacts_list/{id}")
    public String contactsDetails(@PathVariable int id, Model model){
        model.addAttribute("contacts", activityAuditService.getUserContacts(id));
        return "contacts";
    }

    @GetMapping("adresses_list/{id}")
    public String adressesDetails(@PathVariable int id, Model model){
        model.addAttribute("adresses", activityAuditService.getUserAdresses(id));
        return "adresses";
    }
}
