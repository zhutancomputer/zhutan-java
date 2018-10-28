package trlygjj.tanruiliyigenjinjin.service;


import trlygjj.tanruiliyigenjinjin.domain.Home;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IHomeService {
    int deleteByPrimaryKey(Long id);

    int insert(Home record);

    Home selectByPrimaryKey(Long id);

    List<Home> selectAll();

    int updateByPrimaryKey(Home record);

    List<Home> selectByEmployeeId(Long employeeId);

    void deleteByEmployeeId(Long employeeId);

}
