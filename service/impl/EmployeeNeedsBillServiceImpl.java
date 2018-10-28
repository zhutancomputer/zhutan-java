package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.EmployeeNeedsBill;
import trlygjj.tanruiliyigenjinjin.mapper.EmployeeNeedsBillMapper;
import trlygjj.tanruiliyigenjinjin.service.IEmployeeNeedsBillService;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class EmployeeNeedsBillServiceImpl implements IEmployeeNeedsBillService {
    @Autowired
    private EmployeeNeedsBillMapper employeeNeedsBillMapper;
    @Override
    public int deleteByEmployeeNeedsId(Long employeeNeedsId) {

        return employeeNeedsBillMapper.deleteByEmployeeNeedsId(employeeNeedsId);
    }

    @Override
    public int insert(EmployeeNeedsBill record) {
        return employeeNeedsBillMapper.insert(record);
    }

    @Override
    public EmployeeNeedsBill selectByPrimaryKey(Long id) {
        return employeeNeedsBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EmployeeNeedsBill> selectAll() {
        return employeeNeedsBillMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(EmployeeNeedsBill record) {
        return employeeNeedsBillMapper.updateByPrimaryKey(record);
    }

    public List<EmployeeNeedsBill> selectEmployeeNeedsBillByEmployeeNeedsId(Long employeeNeedsId) {
        return employeeNeedsBillMapper.selectEmployeeNeedsBillByEmployeeNeedsId(employeeNeedsId);
    }


}
