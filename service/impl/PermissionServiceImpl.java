package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import trlygjj.tanruiliyigenjinjin.domain.Permission;
import trlygjj.tanruiliyigenjinjin.mapper.PermissionMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IPermissionService;
import trlygjj.tanruiliyigenjinjin.util.PermissionName;

import java.util.*;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    //请求映射处理映射器
    @Autowired
    private RequestMappingHandlerMapping rmhm;

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = permissionMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Permission> rows = permissionMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void load() {
        //获取所有的权限
        List<Permission> permissions = permissionMapper.selectAll();
        //权限去重操作
        Set<String> set = new HashSet<>();
        for (Permission permission : permissions) {
            set.add(permission.getResource());
        }

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();

        Collection<HandlerMethod> methods = handlerMethods.values();

        for (HandlerMethod method : methods) {

            RequiresPermissions methodAnnotation = method.getMethodAnnotation(RequiresPermissions.class);

            if (methodAnnotation == null) {
                continue;
            }

            String resource = methodAnnotation.value()[0];

            if (set.contains(resource)) {
                continue;
            }

            PermissionName annotation = method.getMethodAnnotation(PermissionName.class);

            String name = annotation.value();

            Permission permission = new Permission();

            permission.setResource(resource);

            permission.setName(name);

            permissionMapper.insert(permission);
        }

    }

    public List<Permission> queryPermissionByRoleId(Long roleId) {
        return permissionMapper.queryPermissionByRoleId(roleId);
    }

    @Override
    public List<String> queryPermissionResourceByEmployeeId(Long id) {
        return permissionMapper.queryPermissionResourceByEmployeeId(id);
    }
}
