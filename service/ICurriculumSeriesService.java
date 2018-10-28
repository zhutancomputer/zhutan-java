package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.CurriculumSeries;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ICurriculumSeriesService {
    int deleteByPrimaryKey(Long id);

    int insert(CurriculumSeries record);

    CurriculumSeries selectByPrimaryKey(Long id);

    List<CurriculumSeries> selectAll();

    int updateByPrimaryKey(CurriculumSeries record);

    PageResult query(QueryObject qo);


}
