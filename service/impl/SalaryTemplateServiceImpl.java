package trlygjj.tanruiliyigenjinjin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.Salary;
import trlygjj.tanruiliyigenjinjin.domain.SalaryTemplate;
import trlygjj.tanruiliyigenjinjin.mapper.SalaryTemplateMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IEmployeeService;
import trlygjj.tanruiliyigenjinjin.service.ISalaryService;
import trlygjj.tanruiliyigenjinjin.service.ISalaryTemplateService;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class SalaryTemplateServiceImpl implements ISalaryTemplateService {
    @Autowired
    private SalaryTemplateMapper salaryTemplateMapper;
    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return salaryTemplateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SalaryTemplate record) {
        return salaryTemplateMapper.insert(record);
    }

    @Override
    public SalaryTemplate selectByPrimaryKey(Long id) {
        return salaryTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SalaryTemplate> selectAll() {
        return salaryTemplateMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SalaryTemplate record) {
        Salary salary=new Salary();
        Employee emp = employeeService.selectByPrimaryKey(record.getEmployee().getId());
        salary.setEmployee(emp);
        salary.setDepartment(emp.getDepartment());
        salary.setBaseSalary(record.getBasesalary());
        salary.setAccumulationFund(record.getAccumulationfund());
        salary.setBankcardNumber(record.getBankcardnumber());
        salary.setBonus(record.getBonus());
        salary.setCoefficient(record.getCoefficient());
        salary.setLateday(record.getLateday());
        salary.setMonth(new Date());
        salary.setOvertime(record.getOvertime());
        salary.setPersonalIncome(record.getPersonalincome());
        salary.setRealWages(record.getRealwages());
        salary.setSocialInsurance(record.getSocialinsurance());
        salary.setWorkday(record.getWorkday());
        salaryService.insert(salary);
        return salaryTemplateMapper.updateByPrimaryKey(record);
    }

    public PageResult query(QueryObject qo) {
        return null;
    }


    public SalaryTemplate selectByEmployeeId(Long employeeId) {
        return salaryTemplateMapper.selectByEmployeeId(employeeId);
    }
}
