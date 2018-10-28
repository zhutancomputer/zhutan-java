package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.ClassRoom;
import trlygjj.tanruiliyigenjinjin.mapper.ClassRoomMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IClassRoomService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class ClassRoomServiceImpl implements IClassRoomService {
    @Autowired
    private ClassRoomMapper classRoomMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return classRoomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ClassRoom record) {
        return classRoomMapper.insert(record);
    }

    @Override
    public ClassRoom selectByPrimaryKey(Long id) {
        return classRoomMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ClassRoom> selectAll() {
        return classRoomMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ClassRoom record) {
        return classRoomMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = classRoomMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<ClassRoom> rows = classRoomMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 对状态值进行改变
     * @param classRoomId 根据选中的id
     */
    @Override
    public void changeState(Long classRoomId) {
        classRoomMapper.changeState(classRoomId);
    }
}
