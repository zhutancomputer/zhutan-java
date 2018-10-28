package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.DataDictionary;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IDataDictionaryService {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    List<DataDictionary> selectAll();

    int updateByPrimaryKey(DataDictionary record);

    PageResult query(QueryObject qo);

    DataDictionary selectDataDictionaryItemsBySn(String sn);

    /**
     * 获取大客户的重要程度
     * @param sn
     * @return
     */
    List<DataDictionary> getImportantBySn(DataDictionary sn);

    /**
     * 获取大客户的意向程度
     * @param sn
     * @return
     */
    List<DataDictionary> getIntentionBySn(DataDictionary sn);

    /**
     * 获取大客户的意向学科
     * @param sn
     * @return
     */
    List<DataDictionary> getIntentionSubjectBySn(DataDictionary sn);

    /**
     * 获取大客户的意向校区
     * @param sn
     * @return
     */
    List<DataDictionary> getIntentionDistrictBySn(DataDictionary sn);

    /**
     * 获取大客户的规定学历
     * @param sn
     * @return
     */
    List<DataDictionary> getEducationBySn(DataDictionary sn);

    /**
     * 获取大客户的学校体制
     * @param sn
     * @return
     */
    List<DataDictionary> getSchoolSystemBySn(DataDictionary sn);

}
