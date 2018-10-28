package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.SystemLog;
import trlygjj.tanruiliyigenjinjin.mapper.SystemLogMapper;
import trlygjj.tanruiliyigenjinjin.service.ISystemLogService;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemLog record) {
        return systemLogMapper.insert(record);
    }

    @Override
    public SystemLog selectByPrimaryKey(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemLog> selectAll() {
        return systemLogMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SystemLog record) {
        return systemLogMapper.updateByPrimaryKey(record);
    }
}
