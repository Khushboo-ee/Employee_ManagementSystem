package com.ems.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ems.entity.Employee;
import com.ems.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	private EmpService empservice;

	@GetMapping("/")
	public String home(Model m) {
		
		List<Employee> emp = empservice.getAllEmp();
		m.addAttribute("employee",emp);
		return "index";
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e,HttpSession session) {
		System.out.println(e);
		
		empservice.addEmp(e);
		session.setAttribute("msg", "Employee added successfully");
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmp(@PathVariable int id, Model m) {
		
		Employee e = empservice.getEmpById(id);
		
		m.addAttribute("emp", e);
		
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e,HttpSession session) {
		
		session.setAttribute("msg", "Employee data updated successfully");
		empservice.addEmp(e);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		
		empservice.delEmp(id);
		session.setAttribute("msg", "Employee record deleted successfully");
		return "redirect:/";
		
	}
}
