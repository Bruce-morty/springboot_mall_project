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
        map.put("hotKeywordList", keywordList);

        // 默认的热搜值的判断
        if (keywordList.size() != 0) {
            Keyword keyword1 = keywordList.get(0);// 默认搜索选项值
            map.put("defaultKeyword", keyword1);
        } else {
            map.put("defaultKeyword", null);
        }

        // 个人定制的历史记录的录入和查询
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");// 获取userId
        SearchHistoryExample historyExample = new SearchHistoryExample();

        if (userId == null) return map;
        if (userId != null) {
            historyExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
            List<SearchHistory> searchHistoryList = searchHistoryMapper.selectByExample(historyExample);
            map.put("historyKeywordList", searchHistoryList);
        }
        return map;
    }


    /**
     * 清除所有的历史记录
     * @return
     */
    @Override
    public int cleanHistorySearch() {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();

        searchHistoryExample.createCriteria().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        int i = searchHistoryMapper.deleteByExample(searchHistoryExample);
        return i;
    }

    /**
     * 联想词根
     *
     * @param keyword
     * @return
     */
    @Override
    public List<String> associationHelper(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.createCriteria().andKeywordLike("%" + keyword + "%").andDeletedEqualTo(false);
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        List<String> arrayList = new ArrayList<>();
        for (Keyword keyword1 : keywords) {
            arrayList.add(keyword1.getKeyword());
        }
        return arrayList;
    }
}
