package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.Linkman;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.LinkmanQueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface ILinkmanService {
     void deleteByPrimaryKey(Long id);

     void insert(Linkman record);

    Linkman selectByPrimaryKey(Long id);

    List<Linkman> selectAll();

    void updateByPrimaryKey(Linkman record);

    PageResult query(LinkmanQueryObject qo);

    /**
     * 通过linkman 中的schoolId = bigClientId 获取学校的联系人
     * @param bigClientId
     * @return
     */
    List<Linkman> getLinkmanBySchoolId(Long bigClientId);
}
