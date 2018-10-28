package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Work;
import trlygjj.tanruiliyigenjinjin.mapper.WorkMapper;
import trlygjj.tanruiliyigenjinjin.service.IWorkService;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class WorkServiceImpl implements IWorkService {
    @Autowired
    private WorkMapper workMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return workMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Work record) {
        return workMapper.insert(record);
    }

    @Override
    public Work selectByPrimaryKey(Long id) {
        return workMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Work> selectAll() {
        return workMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Work record) {
        return workMapper.updateByPrimaryKey(record);
    }

    public List<Work> selectByEmployeeId(Long employeeId) {
        return workMapper.selectByEmployeeId(employeeId);
    }

    public void deleteByEmployeeId(Long employeeId) {
        workMapper.deleteByEmployeeId(employeeId);
    }

}
