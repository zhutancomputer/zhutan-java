package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.EmployeeQueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    PageResult query(EmployeeQueryObject qo);

    List<Long> selectRoleByEmployeeId(Long employeeId);


    List<Employee>  selectUsernameByJobs(String  jobs);

    Employee queryUsername(String username);

    void updateState(Long employeeId);

    void changeState(Long id);

    void changeAdmin(Long id);

    void resetPassword(Long id, String username);

    void uploadImage(String hiddenImagePath, Long employeeId);

    void deblocking(String password);
}
