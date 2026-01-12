package cn.vlooks.www.app.mybatis.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 生产需求查询条件VO
 *
 * @author generator
 */
@Data
public class ProductionDemandQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 产品类别
     */
    private String productCategory;

    /**
     * 场景ID
     */
    private Long scenarioId;

    /**
     * 客户编码
     */
    private String customerCode;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 生产工厂
     */
    private String productionPlant;

    /**
     * 生产资源
     */
    private String productionResource;

    /**
     * 生产日期开始
     */
    private LocalDateTime productionDateStart;

    /**
     * 生产日期结束
     */
    private LocalDateTime productionDateEnd;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;
}
