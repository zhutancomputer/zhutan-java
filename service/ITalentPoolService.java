package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.TalentPool;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ITalentPoolService {
    int deleteByPrimaryKey(Long id);

    int insert(TalentPool record);

    TalentPool selectByPrimaryKey(Long id);

    List<TalentPool> selectAll();

    int updateByPrimaryKey(TalentPool record);

    PageResult query(QueryObject qo);
}
