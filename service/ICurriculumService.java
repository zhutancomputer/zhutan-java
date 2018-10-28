package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Curriculum;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ICurriculumService {
    int deleteByPrimaryKey(Long id);

    int insert(Curriculum record);

    Curriculum selectByPrimaryKey(Long id);

    List<Curriculum> selectAll();

    int updateByPrimaryKey(Curriculum record);

    PageResult query(QueryObject qo);

    /**
     * 查询课程的名称
     * @param curriculumSeriseId
     * @return
     */
    List<Curriculum> selectCurriculumNameBycurriculumSeriseId(Long curriculumSeriseId);
}
