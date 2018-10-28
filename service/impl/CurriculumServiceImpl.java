package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Curriculum;
import trlygjj.tanruiliyigenjinjin.mapper.CurriculumMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ICurriculumService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class CurriculumServiceImpl implements ICurriculumService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return curriculumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Curriculum record) {
        return curriculumMapper.insert(record);
    }

    @Override
    public Curriculum selectByPrimaryKey(Long id) {
        return curriculumMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Curriculum> selectAll() {
        return curriculumMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Curriculum record) {
        return curriculumMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = curriculumMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Curriculum> rows = curriculumMapper.queryForList(qo);
        return new PageResult(total,rows);
    }


    @Override
    public List<Curriculum> selectCurriculumNameBycurriculumSeriseId(Long curriculumSeriseId) {
        return curriculumMapper.selectCurriculumNameBycurriculumSeriseId(curriculumSeriseId);
    }
}
