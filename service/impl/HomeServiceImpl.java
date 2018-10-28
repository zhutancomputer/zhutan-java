package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Home;
import trlygjj.tanruiliyigenjinjin.mapper.HomeMapper;
import trlygjj.tanruiliyigenjinjin.service.IHomeService;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class HomeServiceImpl implements IHomeService {
    @Autowired
    private HomeMapper homeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return homeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Home record) {
        return homeMapper.insert(record);
    }

    @Override
    public Home selectByPrimaryKey(Long id) {
        return homeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Home> selectAll() {
        return homeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Home record) {
        return homeMapper.updateByPrimaryKey(record);
    }

    public List<Home> selectByEmployeeId(Long employeeId) {
        return homeMapper.selectByEmployeeId(employeeId);
    }

    public void deleteByEmployeeId(Long employeeId) {
        homeMapper.deleteByEmployeeId(employeeId);
    }

}
