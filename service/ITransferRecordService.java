package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.TransferRecord;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * 学员转班
 */
public interface ITransferRecordService {

    int insert(TransferRecord record);

    TransferRecord selectByPrimaryKey(Long id);

    List<TransferRecord> selectAll();


    PageResult query(QueryObject qo);

    /**
     * 转班学员审核
     * @param id
     */
    void updateLeaveStudentState(Long id);
}
