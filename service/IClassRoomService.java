package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.ClassRoom;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IClassRoomService {
    int deleteByPrimaryKey(Long id);

    int insert(ClassRoom record);

    ClassRoom selectByPrimaryKey(Long id);

    List<ClassRoom> selectAll();

    int updateByPrimaryKey(ClassRoom record);

    PageResult query(QueryObject qo);

    /**
     * 根据id来改变状态值
     * @param classRoomId
     */

    void changeState(Long classRoomId);
}
