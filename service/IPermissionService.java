package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Permission;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IPermissionService {

    List<Permission> selectAll();

    PageResult query(QueryObject qo);

    void load();

    List<Permission> queryPermissionByRoleId(Long roleId);

    List<String> queryPermissionResourceByEmployeeId(Long id);
}
