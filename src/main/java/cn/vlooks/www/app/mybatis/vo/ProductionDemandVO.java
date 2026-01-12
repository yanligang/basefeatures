package cn.vlooks.www.app.mybatis.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 生产需求查询结果VO
 *
 * @author generator
 */
@Data
public class ProductionDemandVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 生产工厂编码
     */
    private String plantCode;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 类别
     */
    private String category;

    /**
     * 期间开始时间
     */
    private String periodStartTime;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 需求开始时间
     */
    private String demandStartTime;

    /**
     * 需求时间 (同 demandStartTime)
     */
    private String demandTime;

    /**
     * 净需求数量
     */
    private BigDecimal netDemandQuantity;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 平台
     */
    private String platform;

    /**
     * 产品型号
     */
    private String productModel;
}
