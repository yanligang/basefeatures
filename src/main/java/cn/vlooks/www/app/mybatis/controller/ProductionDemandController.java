package cn.vlooks.www.app.mybatis.controller;

import cn.vlooks.www.app.mybatis.entity.ApsProductionDemandPmcNew;
import cn.vlooks.www.app.mybatis.service.IApsProductionDemandPmcNewService;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandQueryVO;
import cn.vlooks.www.app.mybatis.vo.ProductionDemandVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 生产需求 控制器
 *
 * @author generator
 */
@RestController
@RequestMapping("/api/production-demand")
public class ProductionDemandController {

    @Autowired
    private IApsProductionDemandPmcNewService productionDemandService;

    /**
     * 分页查询生产需求
     *
     * @param queryVO 查询条件
     * @return 分页结果
     */
    @PostMapping("/page")
    public ResponseEntity<IPage<ProductionDemandVO>> queryPage(@RequestBody ProductionDemandQueryVO queryVO) {
        IPage<ProductionDemandVO> result = productionDemandService.queryProductionDemandPage(queryVO);
        return ResponseEntity.ok(result);
    }

    /**
     * 查询生产需求列表（不分页）
     *
     * @param queryVO 查询条件
     * @return 结果列表
     */
    @PostMapping("/list")
    public ResponseEntity<List<ProductionDemandVO>> queryList(@RequestBody ProductionDemandQueryVO queryVO) {
        List<ProductionDemandVO> result = productionDemandService.queryProductionDemandList(queryVO);
        return ResponseEntity.ok(result);
    }

    /**
     * 根据ID查询
     *
     * @param id 主键ID
     * @return 实体
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApsProductionDemandPmcNew> getById(@PathVariable Long id) {
        ApsProductionDemandPmcNew entity = productionDemandService.getById(id);
        return ResponseEntity.ok(entity);
    }

    /**
     * 新增
     *
     * @param entity 实体
     * @return 结果
     */
    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody ApsProductionDemandPmcNew entity) {
        boolean result = productionDemandService.save(entity);
        return ResponseEntity.ok(result);
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return 结果
     */
    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody ApsProductionDemandPmcNew entity) {
        boolean result = productionDemandService.updateById(entity);
        return ResponseEntity.ok(result);
    }

    /**
     * 删除
     *
     * @param id 主键ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean result = productionDemandService.removeById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * 批量删除
     *
     * @param ids ID列表
     * @return 结果
     */
    @DeleteMapping("/batch")
    public ResponseEntity<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        boolean result = productionDemandService.removeByIds(ids);
        return ResponseEntity.ok(result);
    }
}
