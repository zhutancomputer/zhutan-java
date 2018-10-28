package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.DataDictionaryItem;
import trlygjj.tanruiliyigenjinjin.mapper.DataDictionaryItemMapper;
import trlygjj.tanruiliyigenjinjin.service.IDataDictionaryItemService;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class DataDictionaryItemServiceImpl implements IDataDictionaryItemService {
    @Autowired
    private DataDictionaryItemMapper dataDictionaryItemMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dataDictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataDictionaryItem record) {
        return dataDictionaryItemMapper.insert(record);
    }


    @Override
    public int updateByPrimaryKey(DataDictionaryItem record) {
        return dataDictionaryItemMapper.updateByPrimaryKey(record);
    }

    public List<DataDictionaryItem> selectByParentId(Long id) {
        return dataDictionaryItemMapper.selectByParentId(id);
    }
}
