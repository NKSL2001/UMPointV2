package my.edu.um.umpoint.modules.booking.dao;

import my.edu.um.umpoint.common.dao.BaseDao;
import my.edu.um.umpoint.modules.booking.entity.AccPaymentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Payment
 *
 * @author Tan Chun Hong tanchunhong717@gmail.com
 * @since 1.0.0 2024-09-08
 */
@Mapper
public interface AccPaymentDao extends BaseDao<AccPaymentEntity> {
	
}