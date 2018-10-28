package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.CustomerFollowRecord;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ICustomerFollowRecordService {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerFollowRecord record);

    CustomerFollowRecord selectByPrimaryKey(Long id);

    List<CustomerFollowRecord> selectAll();

    int updateByPrimaryKey(CustomerFollowRecord record);

    PageResult query(QueryObject qo);

    void audit(Long id);
}
