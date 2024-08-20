package my.edu.um.umpoint.modules.service.dao;

import my.edu.um.umpoint.common.dao.BaseDao;
import my.edu.um.umpoint.modules.service.entity.SvcCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Service Category
 *
 * @author Tan Chun Hong tanchunhong717@gmail.com
 * @since 1.0.0 2024-08-20
 */
@Mapper
public interface SvcCategoryDao extends BaseDao<SvcCategoryEntity> {
	
}