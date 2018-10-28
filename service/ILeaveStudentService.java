package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.LeaveStudent;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * 学员流失
 */
public interface ILeaveStudentService {

    /**
     * 保存学员流失
     * @param record
     */
    void insert(LeaveStudent record);

    /**
     * 查询学员流失
     * @param id
     * @return
     */
    LeaveStudent selectByPrimaryKey(Long id);

    /**
     * 查询全部学员流失
     * @return
     */
    List<LeaveStudent> selectAll();


    /**
     * 学员流失分页查询
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 审核流失学员的转态
     */
    void updateLeaveStudentState(Long  leaveStudentId);

}
