package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Linkman;
import trlygjj.tanruiliyigenjinjin.mapper.LinkmanMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.LinkmanQueryObject;
import trlygjj.tanruiliyigenjinjin.service.ILinkmanService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class LinkmanServiceImpl implements ILinkmanService {
    @Autowired
    private LinkmanMapper linkmanMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
         linkmanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Linkman record) {
         linkmanMapper.insert(record);
    }

    @Override
    public Linkman selectByPrimaryKey(Long id) {
        return linkmanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Linkman> selectAll() {
        return linkmanMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Linkman record) {
         linkmanMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(LinkmanQueryObject qo) {
        int total = linkmanMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Linkman> rows = linkmanMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 通过linkman 中的schoolId = bigClientId 获取学校的联系人
     * @param bigClientId
     * @return
     */

    public List<Linkman> getLinkmanBySchoolId(Long bigClientId) {
        return linkmanMapper.getLinkmanBySchoolId(bigClientId);
    }

}
