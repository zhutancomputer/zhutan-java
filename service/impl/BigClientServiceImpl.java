package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.BigClient;
import trlygjj.tanruiliyigenjinjin.mapper.BigClientMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.BigClientQueryObject;
import trlygjj.tanruiliyigenjinjin.service.IBigClientService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class BigClientServiceImpl implements IBigClientService {
    @Autowired
    private BigClientMapper bigClintMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
         bigClintMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(BigClient record) {
         bigClintMapper.insert(record);
    }

    @Override
    public BigClient selectByPrimaryKey(Long id) {
        return bigClintMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BigClient> selectAll() {
        return bigClintMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(BigClient record) {
         bigClintMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(BigClientQueryObject qo) {
        int total = bigClintMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<BigClient> rows = bigClintMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 更新跟进状态
     * @param bigClient
     */
    public void updateFollowState(BigClient bigClient) {
        bigClient.setFollowStateId(BigClient.HAS_FOLLOW_STATE);

        bigClintMapper.updateFollowState(bigClient);
    }
    /**
     * 更新客户状态
     * @param bigClient
     */
    public void updateContractState(BigClient bigClient) {
        bigClient.setContractStateId(BigClient.FINISH_CONTRACT);
        bigClintMapper.updateContractState(bigClient);
    }


}
