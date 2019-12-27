package top.philxin.service.impl;



import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.philxin.mapper.TopicMapper;
import top.philxin.model.Topic;
import top.philxin.model.TopicExample;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.Generalize_topicService;

import java.util.List;


@Service
public class Generalize_topicServiceImpl implements Generalize_topicService {

@Autowired
    TopicMapper topicMapper;
    @Override
    public List<Topic> queryTopic(PageHelperVo pageHelperVo, String title, String subtitle) {
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
        return topics;
    }
}
