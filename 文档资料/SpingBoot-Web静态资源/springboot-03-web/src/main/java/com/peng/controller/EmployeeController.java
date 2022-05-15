package com.peng.controller;

import com.peng.dao.DepartmentDao;
import com.peng.dao.EmployeeDao;
import com.peng.pojo.Department;
import com.peng.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model){
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加的操作
        System.out.println(employee);
        employeeDao.save(employee);//保存员工的信息
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        Employee employeeByid = employeeDao.getEmployeeByid(id);
        model.addAttribute("emp",employeeByid);
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
