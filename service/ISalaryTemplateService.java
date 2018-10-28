package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.SalaryTemplate;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ISalaryTemplateService {
    int deleteByPrimaryKey(Long id);

    int insert(SalaryTemplate record);

    SalaryTemplate selectByPrimaryKey(Long id);

    List<SalaryTemplate> selectAll();

    int updateByPrimaryKey(SalaryTemplate record);

    PageResult query(QueryObject qo);

    SalaryTemplate selectByEmployeeId(Long employeeId);
}
