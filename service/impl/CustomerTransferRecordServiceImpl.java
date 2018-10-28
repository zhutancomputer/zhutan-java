package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.CustomerTransferRecord;
import trlygjj.tanruiliyigenjinjin.mapper.CustomerTransferRecordMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ICustomerTransferRecordService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class CustomerTransferRecordServiceImpl implements ICustomerTransferRecordService {
    @Autowired
    private CustomerTransferRecordMapper customerTransferRecordMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return customerTransferRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CustomerTransferRecord record) {
        return customerTransferRecordMapper.insert(record);
    }

    @Override
    public CustomerTransferRecord selectByPrimaryKey(Long id) {
        return customerTransferRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerTransferRecord> selectAll() {
        return customerTransferRecordMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CustomerTransferRecord record) {
        return customerTransferRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = customerTransferRecordMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<CustomerTransferRecord> rows = customerTransferRecordMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
}
