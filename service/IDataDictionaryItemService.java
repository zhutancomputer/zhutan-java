package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.DataDictionaryItem;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IDataDictionaryItemService {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionaryItem record);

    int updateByPrimaryKey(DataDictionaryItem record);

    List<DataDictionaryItem> selectByParentId(Long id);
}
