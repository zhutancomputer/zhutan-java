package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.EmployeeNeeds;
import trlygjj.tanruiliyigenjinjin.domain.EmployeeNeedsBill;
import trlygjj.tanruiliyigenjinjin.mapper.EmployeeNeedsMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IEmployeeNeedsBillService;
import trlygjj.tanruiliyigenjinjin.service.IEmployeeNeedsService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class EmployeeNeedsServiceImpl implements IEmployeeNeedsService {
    @Autowired
    private EmployeeNeedsMapper EmployeeNeedsMapper;
    @Autowired
    private IEmployeeNeedsBillService EmployeeNeedsBillService;
    @Override
    public int deleteByPrimaryKey(Long id) {
        //删除之前先删除相关的明细
        EmployeeNeedsBillService.deleteByEmployeeNeedsId(id);
        return EmployeeNeedsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(EmployeeNeeds record) {
        record.setInputdate(new Date());
        //记得加上当前用户
        Employee emp = (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setInputUser(emp);
        record.setApplicant(emp);
        int i = EmployeeNeedsMapper.insert(record);
        List<EmployeeNeedsBill> items = record.getItems();
        if(items!=null&&items.size()!=0){
            for (EmployeeNeedsBill item : items) {
                item.setEmployeeNeedsId(record.getId());
                EmployeeNeedsBillService.insert(item);
            }
        }
        return i;
    }
    @Override
    public EmployeeNeeds selectByPrimaryKey(Long id) {
        return EmployeeNeedsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EmployeeNeeds> selectAll() {
        return EmployeeNeedsMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(EmployeeNeeds record) {
        //先删除所有的明细,在添加新的明细
        EmployeeNeedsBillService.deleteByEmployeeNeedsId(record.getId());
        //根据id查询之前的参数,设置到现在的东西
        EmployeeNeeds oldEmployeeNeeds = EmployeeNeedsMapper.selectByPrimaryKey(record.getId());
        record.setInputUser(oldEmployeeNeeds.getInputUser());
        record.setApplicant(oldEmployeeNeeds.getApplicant());
        record.setInputdate(oldEmployeeNeeds.getInputdate());
        return EmployeeNeedsMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = EmployeeNeedsMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<EmployeeNeeds> rows = EmployeeNeedsMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    public List<EmployeeNeeds> selectUnreviewed() {
        return EmployeeNeedsMapper.selectUnreviewed();
    }

    public List<EmployeeNeeds> selectEmployeeNeedsByRecruitmentPlanId(Long recruitmentPlanId) {
        return EmployeeNeedsMapper.selectEmployeeNeedsByRecruitmentPlanId(recruitmentPlanId);
    }

}
