package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.*;
import trlygjj.tanruiliyigenjinjin.mapper.EmployeeMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.EmployeeQueryObject;
import trlygjj.tanruiliyigenjinjin.service.*;
import trlygjj.tanruiliyigenjinjin.util.SignInException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IWorkService workService;
    @Autowired
    private IEducationService educationService;
    @Autowired
    private IHomeService homeService;
    @Autowired
    private ISalaryTemplateService salaryTemplateService;
    @Override
    public int deleteByPrimaryKey(Long id) {
        employeeMapper.deleteEmployeeAndRoleRelation(id);
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        record.setAdmin(Employee.DEFAULT_ADMIN);
        record.setState(Employee.DEFAULT_STATE);
        record.setPassword(Employee.DEFAULT_PASSWORD);
        int insert = employeeMapper.insert(record);
        //添加依赖
        List<Role> roles = record.getRoles();
        if(roles!=null){
            for (Role role : roles){
                employeeMapper.insertEmployeeAndRoleRelation(record.getId(),role.getId());
            }
        }
        //员工新增时先添加一个基础的工资和员工id和部门对象,还有对应的奖金系数
        SalaryTemplate salaryTemplate=new SalaryTemplate();
        salaryTemplate.setBasesalary(new BigDecimal("5000"));
        salaryTemplate.setEmployee(record);
        salaryTemplate.setCoefficient(new BigDecimal("0.8"));
        salaryTemplateService.insert(salaryTemplate);
        return insert;

    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        int i = employeeMapper.updateByPrimaryKey(record);
        employeeMapper.deleteEmployeeAndRoleRelation(record.getId());
        List<Role> roles = record.getRoles();
        if(roles!=null){
            for (Role role : roles){
                employeeMapper.insertEmployeeAndRoleRelation(record.getId(),role.getId());
            }
        }
        //先把所有的有关的数据全部删除在重新添加
        workService.deleteByEmployeeId(record.getId());
        homeService.deleteByEmployeeId(record.getId());
        educationService.deleteByEmployeeId(record.getId());


        List<Work> works = record.getWorks();
        if(works!=null){
            for (Work work : works) {
                work.setEmployeeId(record.getId());
                workService.insert(work);
            }
        }
        List<Home> homes = record.getHomes();
        if(homes!=null){
            for (Home home : homes) {
                home.setEmployeeId(record.getId());
                homeService.insert(home);
            }
        }
        List<Education> educations = record.getEducations();
        if(educations!=null){
            for (Education education : educations) {
                education.setEmployeeId(record.getId());
                educationService.insert(education);
            }
        }

        return  i;
    }

    @Override
    public PageResult query(EmployeeQueryObject qo) {
        int total = employeeMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Employee> rows = employeeMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    public List<Long> selectRoleByEmployeeId(Long employeeId) {
        return employeeMapper.selectRoleByEmployeeId(employeeId);
    }

    @Override
    public Employee queryUsername(String username) {
        Employee employee = employeeMapper.queryUsername(username);
        return employee;
    }

    public void changeState(Long id) {
        employeeMapper.changeState(id);
    }
    public void changeAdmin(Long id) {
        employeeMapper.changeAdmin(id);
    }

    public void resetPassword(Long id, String username) {
        employeeMapper.resetPassword(id,username);
    }

    public void uploadImage(String hiddenImagePath, Long employeeId) {
        employeeMapper.uploadImage(hiddenImagePath, employeeId);
    }

    public void deblocking(String password) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();

        if (!employee.getPassword().equals(password)){
            throw new SignInException("密码错误");
        }



    }

    public void updateState(Long employeeId) {
        employeeMapper.updateState(employeeId);
    }

    public  List<Employee>  selectUsernameByJobs(String  jobs) {
        return employeeMapper.selectUsernameByJobs(jobs);
    }


}
