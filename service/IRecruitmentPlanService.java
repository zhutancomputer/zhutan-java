package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.RecruitmentPlan;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IRecruitmentPlanService {

    int insert(RecruitmentPlan record);

    RecruitmentPlan selectByPrimaryKey(Long id);

    List<RecruitmentPlan> selectAll();


    PageResult query(QueryObject qo);


}
