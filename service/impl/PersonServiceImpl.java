package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import trlygjj.tanruiliyigenjinjin.domain.Person;
import trlygjj.tanruiliyigenjinjin.mapper.PersonMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IPersonService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private PersonMapper personMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return personMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Person record) {
        return personMapper.insert(record);
    }

    @Override
    public Person selectByPrimaryKey(Long id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Person> selectAll() {
        return personMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Person record) {
        return personMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = personMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Person> rows = personMapper.queryForList(qo);
        return new PageResult(total, rows);
    }
}
