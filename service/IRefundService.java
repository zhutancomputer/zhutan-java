package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Refund;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 * 退款管理
 */
public interface IRefundService {
    int deleteByPrimaryKey(Long id);

    int insert(Refund record);

    Refund selectByPrimaryKey(Long id);

    List<Refund> selectAll();

    int updateByPrimaryKey(Refund record);

    PageResult query(QueryObject qo);

    /**
     * 审核退款情况
     * @param id
     */
    void updateLeaveStudentState(Long id);
}
