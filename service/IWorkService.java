package trlygjj.tanruiliyigenjinjin.service;


import trlygjj.tanruiliyigenjinjin.domain.Work;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IWorkService {
    int deleteByPrimaryKey(Long id);

    int insert(Work record);

    Work selectByPrimaryKey(Long id);

    List<Work> selectAll();

    int updateByPrimaryKey(Work record);

    List<Work> selectByEmployeeId(Long employeeId);

    void deleteByEmployeeId(Long employeeId);

}
