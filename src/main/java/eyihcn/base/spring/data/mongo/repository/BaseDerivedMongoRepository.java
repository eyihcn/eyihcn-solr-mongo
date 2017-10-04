
package eyihcn.base.spring.data.mongo.repository;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;

import eyihcn.base.entity.BaseEntity;

/**
 * 
 * @author Administrator
 *
 *         mongo通用的扩展接口
 */
public interface BaseDerivedMongoRepository<T extends BaseEntity<PK>, PK extends Serializable> {


	Iterable<T> findList(Criteria criteria, int pageSize, int pageNumber, Sort.Direction sortDirection, String... sortFields);

	T findOne(Criteria criteria, Direction sortDirection, String... sortFields);

	boolean exists(Criteria criteria);

	long count(Criteria criteria);

	boolean delete(Criteria criteria);
}
