package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Grade;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IGradeService {
    int deleteByPrimaryKey(Long id);

    int insert(Grade record);

    Grade selectByPrimaryKey(Long id);

    List<Grade> selectAll();

    int updateByPrimaryKey(Grade record);

    PageResult query(QueryObject qo);

    List<Grade> listAllInterentGrade();
}
