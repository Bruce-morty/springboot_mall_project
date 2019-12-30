package top.philxin.service.admin.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.philxin.mapper.TopicMapper;
import top.philxin.model.Topic;
import top.philxin.model.TopicExample;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.admin.Generalize_topicService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class Generalize_topicServiceImpl implements Generalize_topicService {

@Autowired
    TopicMapper topicMapper;
    @Override
    public Map queryTopic(PageHelperVo pageHelperVo, String title, String subtitle) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        topicExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if(title!=null&&title.length()!=0)
        {
            criteria.andTitleLike("%"+title+"%");
        }
        if(subtitle!=null&&subtitle.length()!=0)
        {
            criteria.andSubtitleLike("%"+subtitle+"%");
        }
        criteria.andDeletedEqualTo(false);

        List<Topic> topics = topicMapper.selectByExampleWithBLOBs(topicExample);

        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",topics);
        map.put("total",topicPageInfo.getTotal());
        return map;

    }

    @Override
    public int deleteTopic(Topic topic) {
        int i = topicMapper.deleteByUpdate(topic);
        return i;
    }

    @Override
    public Topic addTopic(Topic topic) {
        topic.setAddTime(new Date());
        topic.setUpdateTime(new Date());
        topicMapper.insertSelective(topic);
        List<Topic> topics = topicMapper.selectByExample(new TopicExample());
        Topic topic1 = topics.get(topics.size() - 1);
        topic.setId(topic1.getId());

        return topic;
    }

    @Override
    public Topic updateTopic(Topic topic) {
        int i = topicMapper.updateByPrimaryKey(topic);
        return topic;
    }
}