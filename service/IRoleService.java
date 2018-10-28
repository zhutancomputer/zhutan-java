package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Role;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IRoleService {
    void deleteByPrimaryKey(Long id);

    void insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    void updateByPrimaryKey(Role record);

    PageResult query(QueryObject qo);

    /**
     * 根据员工id查询角色的sn
     * @param id
     * @return
     */
    List<String> queryRoleSnByEmployeeId(Long id);

}
