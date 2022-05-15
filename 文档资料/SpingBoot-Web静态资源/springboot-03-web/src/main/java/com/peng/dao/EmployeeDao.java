package com.peng.dao;

import com.peng.pojo.Department;
import com.peng.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employeeMap = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employeeMap = new HashMap<Integer,Employee>(); //创建一个部门表

        employeeMap.put(1001,new Employee(1001,"AA","2910919712@qq.com",0,new Department(101,"教学部"),new Date()));
        employeeMap.put(1002,new Employee(1002,"BB","2910917712@qq.com",1,new Department(105,"后勤部"),new Date()));
        employeeMap.put(1003,new Employee(1003,"CC","2910914712@qq.com",0,new Department(104,"运营部"),new Date()));
        employeeMap.put(1004,new Employee(1004,"DD","2910919742@qq.com",1,new Department(103,"教研部"),new Date()));
        employeeMap.put(1005,new Employee(1005,"EE","2910919782@qq.com",1,new Department(102,"市场部"),new Date()));
    }

    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void save(Employee employee){
        if (employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employeeMap.put(employee.getId(),employee);
    }
    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employeeMap.values();
    }
    //通过id查询员工
    public Employee getEmployeeByid(Integer id){
        return employeeMap.get(id);
    }
    //删除员工通过id
    public void delete(Integer id){
        employeeMap.remove(id);
    }
}
