package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.philxin.mapper.GoodsMapper;
import top.philxin.mapper.StorageMapper;
import top.philxin.model.Goods;
import top.philxin.model.Storage;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.GoodsService;
import top.philxin.util.FileUploadUtils;

import java.util.List;

/**
 * @ClassName: GoddsServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 21:09
 * @version: v1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    StorageMapper storageMapper;


    /**
     * 查询商品信息列表
     * @param pageHelperVo
     * @param goodsSn
     * @param name
     * @return
     */
    @Override
    public BaseDataVo queryGoods(PageHelperVo pageHelperVo, String goodsSn, String name) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
//        GoodsExample goodsExample = new GoodsExample();
//        GoodsExample.Criteria criteria = goodsExample.createCriteria();
//        goodsExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
//        if(goodsSn != null && goodsSn.length() != 0) {
//            criteria.andGoodsSnLike("%" + goodsSn + "%");
//        }
//        if(name != null && name.length() != 0) {
//            criteria.andNameLike("%" + name + "%");
//        }
//        BaseDataVo baseDataVo = new BaseDataVo();
//        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
//        baseDataVo.setItems(goods);
//        // 获取总条目
//        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
//        long total = pageInfo.getTotal();
//        baseDataVo.setTotal((int)total);
//        return baseDataVo;
        BaseDataVo baseDataVo = new BaseDataVo();
        List<Goods> goods = goodsMapper.selectGoods(goodsSn, name);
        baseDataVo.setItems(goods);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        long total = pageInfo.getTotal();
        baseDataVo.setTotal((int) total);
        return baseDataVo;
    }

    @Override
    public Storage uploadImage(MultipartFile file) {
        Storage storage = FileUploadUtils.processUploadFile(file);
        int insert = storageMapper.insert(storage);
        if(insert != 1) {
            return null;
        }
        return storage;
    }
}
