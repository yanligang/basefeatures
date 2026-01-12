package cn.vlooks.www.app.mybatis.service;

import cn.vlooks.www.app.mybatis.entity.ApsProductionDemandPmcNew;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandQueryVO;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 生产需求表 服务类
 *
 * @author generator
 */
public interface IApsProductionDemandPmcNewService extends IService<ApsProductionDemandPmcNew> {

    /**
     * 分页查询生产需求
     *
     * @param queryVO 查询条件
     * @return 分页结果
     */
    IPage<ProductionDemandVO> queryProductionDemandPage(ProductionDemandQueryVO queryVO);

    /**
     * 查询生产需求列表（不分页）
     *
     * @param queryVO 查询条件
     * @return 结果列表
     */
    List<ProductionDemandVO> queryProductionDemandList(ProductionDemandQueryVO queryVO);
}
