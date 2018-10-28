package trlygjj.tanruiliyigenjinjin.service;


import trlygjj.tanruiliyigenjinjin.domain.Education;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IEducationService {
    int deleteByPrimaryKey(Long id);

    int insert(Education record);

    Education selectByPrimaryKey(Long id);

    List<Education> selectAll();

    int updateByPrimaryKey(Education record);

    List<Education> selectByEmployeeId(Long employeeId);

    void deleteByEmployeeId(Long employeeId);

}
