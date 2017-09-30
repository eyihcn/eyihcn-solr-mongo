package eyihcn.base.mongo.repository;

import java.io.Serializable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import eyihcn.base.entity.BaseEntity;

public interface BaseMongoRepository<T extends BaseEntity<PK>, PK extends Serializable> extends MongoRepository<T, PK> {

	Iterable<T> findList(Criteria criteria, int pageSize, int pageNumber, Sort.Direction sortDirection, String... sortFields);

	T findOne(Criteria criteria, Direction sortDirection, String... sortFields);

	boolean exists(Criteria criteria);

	long count(Criteria criteria);

	boolean delete(Criteria criteria);
}