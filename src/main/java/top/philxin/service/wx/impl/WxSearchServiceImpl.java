package top.philxin.service.wx.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.KeywordMapper;
import top.philxin.mapper.SearchHistoryMapper;
import top.philxin.model.Keyword;
import top.philxin.model.KeywordExample;
import top.philxin.model.SearchHistory;
import top.philxin.model.SearchHistoryExample;
import top.philxin.service.wx.WxSearchService;

import java.util.*;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */

@Service
public class WxSearchServiceImpl implements WxSearchService {

    @Autowired
    KeywordMapper keywordMapper;

    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    

    @Override
    public Map queryHistory() {

        // 热搜
        KeywordExample example = new KeywordExample();
        example.createCriteria().andIsHotEqualTo(true).andDeletedEqualTo(false);
        List<Keyword> keywordList = keywordMapper.selectByExample(example);
        HashMap<Object, Object> map = new HashMap<>();
        Keyword keyword1 = keywordList.get(0);
        map.put("hotKeywordList", keywordList);
        map.put("defaultKeyword", keyword1);

        // 历史记录
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");
        SearchHistoryExample historyExample = new SearchHistoryExample();
        if (userId != null) {
            historyExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
            List<SearchHistory> searchHistoryList = searchHistoryMapper.selectByExample(historyExample);
            map.put("historyKeywordList", searchHistoryList);
        }
        return map;
    }
}
