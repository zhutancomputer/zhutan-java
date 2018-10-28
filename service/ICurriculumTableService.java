package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.CurriculumTable;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ICurriculumTableService {
    int deleteByPrimaryKey(Long id);

    int insert(CurriculumTable record);

    CurriculumTable selectByPrimaryKey(Long id);

    List<CurriculumTable> selectAll();

    int updateByPrimaryKey(CurriculumTable record);

    PageResult query(QueryObject qo);

    /**
     * 查询CurriculumTable的数据
     * @param date 根据时间
     * @return
     */
    List<CurriculumTable> selectCurriculumTableByDate(String date);
}
