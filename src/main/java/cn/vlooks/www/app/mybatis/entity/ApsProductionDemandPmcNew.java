package cn.vlooks.www.app.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 生产需求表
 *
 * @author generator
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("aps_production_demand_pmc_new")
public class ApsProductionDemandPmcNew implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场景ID
     */
    @TableField("scenario_id")
    private Long scenarioId;

    /**
     * 需求ID
     */
    @TableField("demand_id")
    private Long demandId;

    /**
     * 类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 产品类别
     */
    @TableField("product_category")
    private String productCategory;

    /**
     * 客户编码
     */
    @TableField("customer_code")
    private String customerCode;

    /**
     * 产品编码
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 需求日期
     */
    @TableField("demand_date")
    private LocalDateTime demandDate;

    /**
     * 净需求数量
     */
    @TableField("net_demand_quantity")
    private BigDecimal netDemandQuantity;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
