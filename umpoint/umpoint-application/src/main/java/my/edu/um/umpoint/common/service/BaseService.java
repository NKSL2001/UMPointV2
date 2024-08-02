package my.edu.um.umpoint.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T> {
    Class<T> currentModelClass();

    boolean insert(T entity);

    boolean insertBatch(Collection<T> entityList);

    boolean insertBatch(Collection<T> entityList, int batchSize);

    boolean updateById(T entity);

    boolean update(T entity, Wrapper<T> updateWrapper);

    boolean updateBatchById(Collection<T> entityList);

    boolean updateBatchById(Collection<T> entityList, int batchSize);

    T selectById(Serializable id);

    boolean deleteById(Serializable id);

    boolean deleteBatchIds(Collection<? extends Serializable> idList);
}