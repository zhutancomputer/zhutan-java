package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.BigClient;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.BigClientQueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IBigClientService {
     void deleteByPrimaryKey(Long id);

     void insert(BigClient record);

    BigClient selectByPrimaryKey(Long id);

    List<BigClient> selectAll();

    void updateByPrimaryKey(BigClient record);

    PageResult query(BigClientQueryObject qo);

    /**
     * 更新跟进状态
     * @param bigClient
     */
    void updateFollowState(BigClient bigClient);

    /**
     * 更新客户状态
     * @param bigClient
     */
    void updateContractState(BigClient bigClient);
}
