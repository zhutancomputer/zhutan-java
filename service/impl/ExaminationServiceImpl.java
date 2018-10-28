package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.Examination;
import trlygjj.tanruiliyigenjinjin.domain.PotentialCustomer;
import trlygjj.tanruiliyigenjinjin.mapper.ExaminationMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IExaminationService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class ExaminationServiceImpl implements IExaminationService {
    @Autowired
    private ExaminationMapper examinationMapper;

    /**
     * 通过改变 PotentialCustomer 中的need_test 来删除考试管理的数据
     * @param examinationId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long examinationId) {
        PotentialCustomer potentialCustomer = new PotentialCustomer();
        potentialCustomer.setNeedTest(PotentialCustomer.UNNEED_TEST);
        potentialCustomer.setId(examinationId);
        return examinationMapper.deleteByPrimaryKey(potentialCustomer);
    }


    @Override
    public int updateByPrimaryKey(PotentialCustomer potentialCustomer) {

        return examinationMapper.updateByPrimaryKey(potentialCustomer);
    }

    @Override
    public PageResult query(QueryObject qo) {

        int total = examinationMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Examination> rows = examinationMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    /**
     * 改变审核考试结果 同时录入处理人
     * @param potentialCustomer
     */
    public void changeStateById(PotentialCustomer potentialCustomer) {
        if(potentialCustomer!=null ){
            potentialCustomer.setTestResult(PotentialCustomer.PASS_THE_TEST);
            Employee handler = (Employee) SecurityUtils.getSubject().getPrincipal();
            potentialCustomer.setTestHandler(handler);
            examinationMapper.changeStateById(potentialCustomer);
        }
    }
}
