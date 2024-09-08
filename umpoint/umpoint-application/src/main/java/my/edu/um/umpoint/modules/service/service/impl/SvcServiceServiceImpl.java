package my.edu.um.umpoint.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import my.edu.um.umpoint.common.annotation.DataFilter;
import my.edu.um.umpoint.common.constant.Constant;
import my.edu.um.umpoint.common.page.PageData;
import my.edu.um.umpoint.common.service.impl.CrudServiceImpl;
import my.edu.um.umpoint.modules.service.dao.SvcServiceDao;
import my.edu.um.umpoint.modules.service.dto.SvcBookingRuleDTO;
import my.edu.um.umpoint.modules.service.dto.SvcServiceDTO;
import my.edu.um.umpoint.modules.service.entity.SvcImageEntity;
import my.edu.um.umpoint.modules.service.entity.SvcServiceEntity;
import my.edu.um.umpoint.modules.service.entity.SvcServiceTagEntity;
import my.edu.um.umpoint.modules.service.service.SvcBookingRuleService;
import my.edu.um.umpoint.modules.service.service.SvcImageService;
import my.edu.um.umpoint.modules.service.service.SvcServiceService;
import cn.hutool.core.util.StrUtil;
import my.edu.um.umpoint.modules.service.service.SvcServiceTagService;
import my.edu.um.umpoint.modules.space.dto.SpcBookingRuleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service
 *
 * @author Tan Chun Hong tanchunhong717@gmail.com
 * @since 1.0.0 2024-08-20
 */
@Service
public class SvcServiceServiceImpl extends CrudServiceImpl<SvcServiceDao, SvcServiceEntity, SvcServiceDTO> implements SvcServiceService {

    @Autowired
    private SvcServiceTagService svcServiceTagService;

    @Autowired
    private SvcImageService svcImageService;

    @Autowired
    private SvcBookingRuleService svcBookingRuleService;

    @Override
    public QueryWrapper<SvcServiceEntity> getWrapper(Map<String, Object> params){
        String name = (String)params.get("name");

        QueryWrapper<SvcServiceEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(name), "name", name);

        return wrapper;
    }

    @Override
    @DataFilter(tableAlias = "s")
    public PageData<SvcServiceDTO> page(Map<String, Object> params) {
        paramsToLike(params, "name");

        IPage<SvcServiceEntity> page = getPage(params, Constant.CREATE_DATE, false);

        List<SvcServiceEntity> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), currentDtoClass());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SvcServiceDTO dto) {
        super.save(dto);
        updateServiceImage(dto);
        updateServiceTag(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SvcServiceDTO dto) {
        updateBookingRule(dto);
        super.update(dto);
        updateServiceImage(dto);
        updateServiceTag(dto);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyDefaultBookingRule(Long[] ids) {
        List<SvcServiceEntity> svcServiceEntities = baseDao.selectBatchIds(Arrays.asList(ids));
        List<Long> bookingRuleIds = new ArrayList<>();

        for (SvcServiceEntity svcServiceEntity : svcServiceEntities) {
            Long bookingRuleId = svcServiceEntity.getBookingRuleId();
            if (bookingRuleId != null && bookingRuleId != 0)
                bookingRuleIds.add(bookingRuleId);
            svcServiceEntity.setBookingRuleId(0L);
        }

        if (!bookingRuleIds.isEmpty())
            svcBookingRuleService.deleteBatchIds(bookingRuleIds);
        updateBatchById(svcServiceEntities);
    }
    
    private void updateBookingRule(SvcServiceDTO dto) {
        SvcBookingRuleDTO svcBookingRuleDTO = dto.getSvcBookingRuleDTO();
        if (svcBookingRuleDTO != null) {
            SvcBookingRuleDTO defaultBookingRuleDTO = svcBookingRuleService.get(0L);
            if (dto.getBookingRuleId() == null) {
                //Have no booking rule yet
                if (svcBookingRuleDTO.equals(defaultBookingRuleDTO)) {
                    //Apply default booking rule
                    dto.setBookingRuleId(0L);
                } else {
                    //Create new booking rule
                    svcBookingRuleService.save(svcBookingRuleDTO);
                    dto.setBookingRuleId(svcBookingRuleDTO.getId());
                }
            } else if (dto.getBookingRuleId() == 0) {
                //Using default booking rule before
                if (!svcBookingRuleDTO.equals(defaultBookingRuleDTO)) {
                    //Change booking rule, need to create new one
                    svcBookingRuleService.save(svcBookingRuleDTO);
                    dto.setBookingRuleId(svcBookingRuleDTO.getId());
                }
            } else
                svcBookingRuleService.update(svcBookingRuleDTO);
        }
    }

    private void updateServiceImage(SvcServiceDTO dto) {
        if (dto.getSvcImageDTOList() != null) {
            List<SvcImageEntity> imageEntityList = dto.getSvcImageDTOList()
                    .stream()
                    .map((svcImageDTO) -> {
                        SvcImageEntity entity = new SvcImageEntity();
                        entity.setId(svcImageDTO.getId());
                        entity.setServiceId(dto.getId());
                        entity.setImageUrl(svcImageDTO.getImageUrl());
                        return entity;
                    }).toList();

            svcImageService.deleteByServiceId(dto.getId());
            svcImageService.insertBatch(imageEntityList);
        }
    }

    private void updateServiceTag(SvcServiceDTO dto) {
        if (dto.getSvcTagDTOList() != null) {
            List<SvcServiceTagEntity> tagEntityList = dto.getSvcTagDTOList()
                    .stream()
                    .map((svcTagDao) -> {
                        SvcServiceTagEntity entity = new SvcServiceTagEntity();
                        entity.setServiceId(dto.getId());
                        entity.setTagId(svcTagDao.getId());
                        return entity;
                    }).toList();

            svcServiceTagService.deleteByServiceId(dto.getId());
            svcServiceTagService.insertBatch(tagEntityList);
        }
    }
}