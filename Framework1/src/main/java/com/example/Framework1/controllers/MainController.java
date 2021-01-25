/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Framework1.controllers;

import com.example.Framework1.models.Department;
import com.example.Framework1.models.Employee;
import com.example.Framework1.repositories.DepartmentRepository;
import com.example.Framework1.repositories.EmployeeRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author NovaYulianti
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String main() {

        return "index";
    }

    @Autowired
    DepartmentRepository departmentRepository;

    //Department  
    @RequestMapping("/department")
    public String showDepartmentList(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("department", new Department());

        //Department department = new Department();
        //model.addAttribute("department", department);
        return "department/form_department";
    }

    //Save Department
    @PostMapping("/saveDepartment")
    public String saveDepartment(Department department) {
        departmentRepository.save(department);
        return "redirect:/department";
    }

    //Edit Department
    @RequestMapping("/editDepartment/{id}")
    public ModelAndView showEditDepartment(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("department/edit_department");
        Department department = departmentRepository.findById(id).get();

        mav.addObject("department", department);

        return mav;
    }

    //Delete Department
    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable(name = "id") String id) {
        departmentRepository.deleteById(id);
        return "redirect:/department";
    }

    
    //Employee --------------------------------------------------------------------------------  
    
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/employee")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("employee", new Employee());
        
        model.addAttribute("departmentList", departmentRepository.findAll());

        return "employee/form_employee";
    }

    //Save Department
    @PostMapping("/saveEmployee")
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    //Edit Department
    @RequestMapping("/editEmployee/{id}")
    public ModelAndView showEditEmployee(Model model, @PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("employee/edit_employee");
        Employee employee = employeeRepository.findById(id).get();

        model.addAttribute("departmentList", departmentRepository.findAll());
        
        mav.addObject("employee", employee);

        return mav;
    }

    //Delete Department
    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(name = "id") String id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    
}
