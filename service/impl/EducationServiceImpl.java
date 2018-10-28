package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Education;
import trlygjj.tanruiliyigenjinjin.mapper.EducationMapper;
import trlygjj.tanruiliyigenjinjin.service.IEducationService;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class EducationServiceImpl implements IEducationService {
    @Autowired
    private EducationMapper educationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return educationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Education record) {
        return educationMapper.insert(record);
    }

    @Override
    public Education selectByPrimaryKey(Long id) {
        return educationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Education> selectAll() {
        return educationMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Education record) {
        return educationMapper.updateByPrimaryKey(record);
    }

    public List<Education> selectByEmployeeId(Long employeeId) {
        return educationMapper.selectByEmployeeId(employeeId);
    }

    public void deleteByEmployeeId(Long employeeId) {
        educationMapper.deleteByEmployeeId(employeeId);
    }

}
