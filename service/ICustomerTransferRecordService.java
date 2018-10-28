package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.CustomerTransferRecord;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ICustomerTransferRecordService {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerTransferRecord record);

    CustomerTransferRecord selectByPrimaryKey(Long id);

    List<CustomerTransferRecord> selectAll();

    int updateByPrimaryKey(CustomerTransferRecord record);

    PageResult query(QueryObject qo);
}
