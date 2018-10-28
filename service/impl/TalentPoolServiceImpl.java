package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.TalentPool;
import trlygjj.tanruiliyigenjinjin.mapper.TalentPoolMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ITalentPoolService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class TalentPoolServiceImpl implements ITalentPoolService {
    @Autowired
    private TalentPoolMapper talentPoolMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return talentPoolMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TalentPool record) {
        return talentPoolMapper.insert(record);
    }

    @Override
    public TalentPool selectByPrimaryKey(Long id) {
        return talentPoolMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TalentPool> selectAll() {
        return talentPoolMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TalentPool record) {
        return talentPoolMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = talentPoolMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<TalentPool> rows = talentPoolMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
}
