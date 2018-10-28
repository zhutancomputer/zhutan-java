package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Salary;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.query.SalaryQueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ISalaryService {
    int deleteByPrimaryKey(Long id);

    int insert(Salary record);

    Salary selectByPrimaryKey(Long id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);

    PageResult query(QueryObject qo);

    Salary selectByEmployeeId(Long employeeId);
    PageResult selectByCurrentEmployee(SalaryQueryObject qo);
}
