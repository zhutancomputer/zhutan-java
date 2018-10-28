package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.EmployeeNeeds;
import trlygjj.tanruiliyigenjinjin.domain.RecruitmentPlan;
import trlygjj.tanruiliyigenjinjin.mapper.RecruitmentPlanMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IEmployeeNeedsService;
import trlygjj.tanruiliyigenjinjin.service.IRecruitmentPlanService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class RecruitmentPlanServiceImpl implements IRecruitmentPlanService {
    @Autowired
    private RecruitmentPlanMapper recruitmentPlanMapper;
    @Autowired
    private IEmployeeNeedsService employeeNeedsService;

    @Override
    public int insert(RecruitmentPlan record) {
        Employee emp = (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setInputUser(emp);
        int i = recruitmentPlanMapper.insert(record);
        List<Long> employeeNeedsIds = record.getEmployeeNeedsIds();
        if(employeeNeedsIds!=null){
            for (Long employeeNeedsId : employeeNeedsIds) {
                EmployeeNeeds employeeNeeds = employeeNeedsService.selectByPrimaryKey(employeeNeedsId);
                employeeNeeds.setRecruitPlanId(record.getId());
                employeeNeedsService.updateByPrimaryKey(employeeNeeds);
            }
        }
        return i;
    }

    @Override
    public RecruitmentPlan selectByPrimaryKey(Long id) {
        return recruitmentPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RecruitmentPlan> selectAll() {
        return recruitmentPlanMapper.selectAll();
    }


    @Override
    public PageResult query(QueryObject qo) {
        int total = recruitmentPlanMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<RecruitmentPlan> rows = recruitmentPlanMapper.queryForList(qo);
        System.out.println(rows);
        return new PageResult(total,rows);
    }
}
