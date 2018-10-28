package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.EmployeeNeeds;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IEmployeeNeedsService {
    int deleteByPrimaryKey(Long id);

    int insert(EmployeeNeeds record);

    EmployeeNeeds selectByPrimaryKey(Long id);

    List<EmployeeNeeds> selectAll();

    int updateByPrimaryKey(EmployeeNeeds record);

    PageResult query(QueryObject qo);

    List<EmployeeNeeds> selectUnreviewed();

    List<EmployeeNeeds> selectEmployeeNeedsByRecruitmentPlanId(Long recruitmentPlanId);
}
