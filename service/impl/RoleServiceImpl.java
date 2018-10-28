package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Permission;
import trlygjj.tanruiliyigenjinjin.domain.Role;
import trlygjj.tanruiliyigenjinjin.domain.SystemMenu;
import trlygjj.tanruiliyigenjinjin.mapper.RoleMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IRoleService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        roleMapper.deleteRoleAndPermissionRelation(id);
        roleMapper.deleteRoleAndSystemMenuRelation(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Role record) {
        roleMapper.insert(record);
        List<Permission> permissions = record.getPermissions();
        for (Permission permission : permissions) {
            roleMapper.saveRoleAndPermissionRelation(record.getId(), permission.getId());
        }
        List<SystemMenu> systemMenus = record.getSystemMenus();
        for (SystemMenu systemMenu : systemMenus){
            roleMapper.saveRoleAndSystemMenuRelation(record.getId(),systemMenu.getId());
        }
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Role record) {
        roleMapper.deleteRoleAndPermissionRelation(record.getId());
        roleMapper.deleteRoleAndSystemMenuRelation(record.getId());
        roleMapper.updateByPrimaryKey(record);
        List<Permission> permissions = record.getPermissions();
        for (Permission permission : permissions) {
            roleMapper.saveRoleAndPermissionRelation(record.getId(), permission.getId());
        }
        List<SystemMenu> systemMenus = record.getSystemMenus();
        for (SystemMenu systemMenu : systemMenus){
            roleMapper.saveRoleAndSystemMenuRelation(record.getId(),systemMenu.getId());
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = roleMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Role> rows = roleMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public List<String> queryRoleSnByEmployeeId(Long id) {
        return roleMapper.queryRoleSnByEmployeeId(id);
    }
}
