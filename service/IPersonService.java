package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Person;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IPersonService {
    int deleteByPrimaryKey(Long id);

    int insert(Person record);

    Person selectByPrimaryKey(Long id);

    List<Person> selectAll();

    int updateByPrimaryKey(Person record);

    PageResult query(QueryObject qo);
}
