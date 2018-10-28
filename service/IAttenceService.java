package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Attence;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IAttenceService {
    int deleteByPrimaryKey(Long id);

    int insert(Attence record);

    Attence selectByPrimaryKey(Long id);

    List<Attence> selectAll();

    int updateByPrimaryKey(Attence record);

    PageResult query(QueryObject qo);

    void signIn();

    void signOut();
}
