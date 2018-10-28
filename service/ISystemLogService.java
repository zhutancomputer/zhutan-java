package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.SystemLog;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ISystemLogService {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);
}
