package cn.vlooks.www.app.mybatis.mapper;

import cn.vlooks.www.app.mybatis.entity.ApsProductionDemandPmcNew;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandQueryVO;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产需求表 Mapper 接口
 *
 * @author generator
 */
@Mapper
public interface ApsProductionDemandPmcNewMapper extends BaseMapper<ApsProductionDemandPmcNew> {

    /**
     * 查询生产需求列表（带分页）
     *
     * @param page     分页参数
     * @param queryVO  查询条件
     * @return 分页结果
     */
    IPage<ProductionDemandVO> selectProductionDemandPage(Page<ProductionDemandVO> page,
                                                          @Param("query") ProductionDemandQueryVO queryVO);

    /**
     * 查询生产需求列表（不分页）
     *
     * @param queryVO 查询条件
     * @return 结果列表
     */
    List<ProductionDemandVO> selectProductionDemandList(@Param("query") ProductionDemandQueryVO queryVO);
}
