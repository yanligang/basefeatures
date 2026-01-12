package cn.vlooks.www.app.mybatis.service.impl;

import cn.vlooks.www.app.mybatis.entity.ApsProductionDemandPmcNew;
import cn.vlooks.www.app.mybatis.mapper.ApsProductionDemandPmcNewMapper;
import cn.vlooks.www.app.mybatis.service.IApsProductionDemandPmcNewService;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandQueryVO;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产需求表 服务实现类
 *
 * @author generator
 */
@Service
public class ApsProductionDemandPmcNewServiceImpl
        extends ServiceImpl<ApsProductionDemandPmcNewMapper, ApsProductionDemandPmcNew>
        implements IApsProductionDemandPmcNewService {

    @Override
    public IPage<ProductionDemandVO> queryProductionDemandPage(ProductionDemandQueryVO queryVO) {
        Page<ProductionDemandVO> page = new Page<>(queryVO.getPageNum(), queryVO.getPageSize());
        return baseMapper.selectProductionDemandPage(page, queryVO);
    }

    @Override
    public List<ProductionDemandVO> queryProductionDemandList(ProductionDemandQueryVO queryVO) {
        return baseMapper.selectProductionDemandList(queryVO);
    }
}
