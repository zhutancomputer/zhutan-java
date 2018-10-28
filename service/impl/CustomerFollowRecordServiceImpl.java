package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.CustomerFollowRecord;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.PotentialCustomer;
import trlygjj.tanruiliyigenjinjin.mapper.CustomerFollowRecordMapper;
import trlygjj.tanruiliyigenjinjin.mapper.PotentialCustomerMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ICustomerFollowRecordService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class CustomerFollowRecordServiceImpl implements ICustomerFollowRecordService {
    @Autowired
    private CustomerFollowRecordMapper customerFollowRecordMapper;
    @Autowired
    private PotentialCustomerMapper potentialCustomerMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return customerFollowRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CustomerFollowRecord record) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        PotentialCustomer potentialCustomer = potentialCustomerMapper.selectByPrimaryKey(record.getId());
        int times = potentialCustomer.getFollowTims();
        times=times+1;
        record.setLastFollowDate(new Date());
        //跟踪记录修改为当前的登录人的id
        record.setFollower(employee);
        //跟踪记录修改跟踪的次数
        record.setFollowTimes(times);


        //修改潜在客户的跟踪人和跟踪次数
        potentialCustomer.setFollowTims(times);
        potentialCustomer.setLastFollowDate(new Date());
        //要修改为当前的登录人
        potentialCustomer.setFollower(employee);
        potentialCustomerMapper.followChange(potentialCustomer);



        return customerFollowRecordMapper.insert(record);
    }

    @Override
    public CustomerFollowRecord selectByPrimaryKey(Long id) {
        return customerFollowRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerFollowRecord> selectAll() {
        return customerFollowRecordMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CustomerFollowRecord record) {
        return customerFollowRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = customerFollowRecordMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<CustomerFollowRecord> rows = customerFollowRecordMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
    //审核记录
    public void audit(Long id) {
        Employee employee  = (Employee) SecurityUtils.getSubject().getPrincipal();
        CustomerFollowRecord customerFollowRecord = customerFollowRecordMapper.selectByPrimaryKey(id);
        customerFollowRecord.setAuditer(employee);
        customerFollowRecord.setAuditState(CustomerFollowRecord.ALREADY_AUDIT);
        customerFollowRecordMapper.audit(customerFollowRecord);

    }
}
