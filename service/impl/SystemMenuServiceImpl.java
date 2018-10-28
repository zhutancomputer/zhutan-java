package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.SystemMenu;
import trlygjj.tanruiliyigenjinjin.mapper.SystemMenuMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ISystemMenuService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemMenu record) {
        return systemMenuMapper.insert(record);
    }

    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SystemMenu record) {
        return systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = systemMenuMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<SystemMenu> rows = systemMenuMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    public List<SystemMenu> queryRootMenu() {
        return systemMenuMapper.queryRootMenu();
    }

    public List<SystemMenu> querylist() {
        return systemMenuMapper.querylist();
    }

    public List<SystemMenu> querySystemMenuByRoleId(Long roleId){
        return systemMenuMapper.querySystemMenuByRoleId(roleId);
    }
}
