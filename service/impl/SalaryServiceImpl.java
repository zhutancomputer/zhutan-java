package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.Salary;
import trlygjj.tanruiliyigenjinjin.mapper.SalaryMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.query.SalaryQueryObject;
import trlygjj.tanruiliyigenjinjin.service.ISalaryService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Salary record) {
        return salaryMapper.insert(record);
    }

    @Override
    public Salary selectByPrimaryKey(Long id) {
        return salaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Salary> selectAll() {
        return salaryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Salary record) {
        return salaryMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = salaryMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Salary> rows = salaryMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public PageResult selectByCurrentEmployee(SalaryQueryObject qo) {
        int total = salaryMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        Employee employee = (Employee)SecurityUtils.getSubject().getPrincipal();
        qo.setEmployeeId(employee.getId());
        List<Salary> rows = salaryMapper.selectByCurrentEmployee(qo);
        return new PageResult(total, rows);
    }

    public Salary selectByEmployeeId(Long employeeId) {
        return salaryMapper.selectByEmployeeId(employeeId);
    }
}
