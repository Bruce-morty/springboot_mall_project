package top.philxin.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.LogMapper;
import top.philxin.model.Log;
import top.philxin.service.admin.LogRecordService;


/**
 * @ClassName: LogRecordServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/29 0029 13:52
 * @version: v1.0
 */
@Service
public class LogRecordServiceImpl implements LogRecordService {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean addLog(Log log) {
        int insert = logMapper.insert(log);
        if(insert != 1) {
            return false;
        }
        return true;
    }
}
