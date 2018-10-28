package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.CurriculumSeries;
import trlygjj.tanruiliyigenjinjin.mapper.CurriculumSeriesMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ICurriculumSeriesService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class CurriculumSeriesServiceImpl implements ICurriculumSeriesService {
    @Autowired
    private CurriculumSeriesMapper curriculumSeriesMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return curriculumSeriesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CurriculumSeries record) {
        return curriculumSeriesMapper.insert(record);
    }

    @Override
    public CurriculumSeries selectByPrimaryKey(Long id) {
        return curriculumSeriesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CurriculumSeries> selectAll() {
        return curriculumSeriesMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CurriculumSeries record) {
        return curriculumSeriesMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = curriculumSeriesMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<CurriculumSeries> rows = curriculumSeriesMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

}
