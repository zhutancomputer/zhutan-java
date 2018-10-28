package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.DataDictionary;
import trlygjj.tanruiliyigenjinjin.mapper.DataDictionaryItemMapper;
import trlygjj.tanruiliyigenjinjin.mapper.DataDictionaryMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IDataDictionaryService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class DataDictionaryServiceImpl implements IDataDictionaryService {
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    private DataDictionaryItemMapper dataDictionaryItemMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        dataDictionaryItemMapper.deleteByParentId(id);
        return dataDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataDictionary record) {
        return dataDictionaryMapper.insert(record);
    }

    @Override
    public DataDictionary selectByPrimaryKey(Long id) {
        return dataDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DataDictionary> selectAll() {
        return dataDictionaryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(DataDictionary record) {
        return dataDictionaryMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = dataDictionaryMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<DataDictionary> rows = dataDictionaryMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    public DataDictionary selectDataDictionaryItemsBySn(String sn) {
        DataDictionary dataDictionary= dataDictionaryMapper.selectDataDictionaryItemsBySn(sn);

        return dataDictionary;
    }

    /**
     * 获取大客户的重要程度
     * @param sn
     * @return
     */
    public List<DataDictionary> getImportantBySn(DataDictionary sn) {
        return dataDictionaryMapper.getImportantBySn(sn);
    }

    /**
     * 获取大客户的意向程度
     * @param sn
     * @return
     */
    public List<DataDictionary> getIntentionBySn(DataDictionary sn) {
        return dataDictionaryMapper.getIntentionBySn(sn);
    }
    /**
     * 获取大客户的意向学科
     * @param sn
     * @return
     */
    public List<DataDictionary> getIntentionSubjectBySn(DataDictionary sn) {
        return dataDictionaryMapper.getIntentionSubjectBySn(sn);
    }

    /**
     * 获取大客户的意向校区
     * @param sn
     * @return
     */
    public List<DataDictionary> getIntentionDistrictBySn(DataDictionary sn) {
        return dataDictionaryMapper.getIntentionDistrictBySn(sn);
    }

    /**
     * 获取大客户的规定学历
     * @param sn
     * @return
     */
    public List<DataDictionary> getEducationBySn(DataDictionary sn) {
        return dataDictionaryMapper.getEducationBySn(sn);
    }

    /**
     * 获取大客户的学校体制
     * @param sn
     * @return
     */
    public List<DataDictionary> getSchoolSystemBySn(DataDictionary sn) {
        return dataDictionaryMapper.getSchoolSystemBySn(sn);
    }

}
