package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.EmployeeNeedsBill;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IEmployeeNeedsBillService {
    int deleteByEmployeeNeedsId(Long employeeNeedsId);

    int insert(EmployeeNeedsBill record);

    EmployeeNeedsBill selectByPrimaryKey(Long id);

    List<EmployeeNeedsBill> selectAll();

    int updateByPrimaryKey(EmployeeNeedsBill record);

    /**
     * 根据用人需求的id来查询明细
     * @param employeeNeedsId 用人需求的id
     * @return 封装了明细的集合
     */
    List<EmployeeNeedsBill> selectEmployeeNeedsBillByEmployeeNeedsId(Long employeeNeedsId);
}
