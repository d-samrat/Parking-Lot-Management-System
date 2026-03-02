package com.parkinglot.controller;

import com.parkinglot.enums.SpotType;
import com.parkinglot.service.AdminService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/add-lot")
        public String addLotForm(){
            return "admin/add-lot";
        }

    @PostMapping("/add-lot")
        public String addLot(@RequestParam String name){
            adminService.createParkingLot(name);
            return "redirect:/admin";
    }

    @GetMapping("/add-floor")
    public String addFloorForm(){
        return "admin/add-floor";
    }

    @PostMapping("/add-floor")
        public String addFloor(@RequestParam long lotId, @RequestParam int floorNumber){
            adminService.addFloor(lotId, floorNumber);
            return "redirect:/admin";
        }

    @GetMapping("/add-spot")
    public String addSpotForm(Model model){
        model.addAttribute("spotTypes", SpotType.values());
        return "admin/add-spot";
    }

    @PostMapping("/add-spot")
    public String addSpot(@RequestParam long floorId, @RequestParam String spotNumber, @RequestParam SpotType spotType){
        adminService.addSpot(floorId, spotNumber, spotType);
        return "redirect:/admin";
    }

    @GetMapping("/tickets")
    public String viewTickets(Model model){
        model.addAttribute("tickets",adminService.getAllTickets());
        return "admin/tickets";
    }

    @GetMapping
    public String adminHome(){
        return "admin/index"; // create this page
    }
}
