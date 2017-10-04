package eyihcn.base.spring.data.mongo.repository;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import eyihcn.base.entity.BaseEntity;
import eyihcn.utils.MyBeanUtil;

public class BaseMongoRepositoryImpl<T extends BaseEntity<PK>, PK extends Serializable> extends SimpleMongoRepository<T, PK> implements BaseMongoRepository<T, PK> {

	private final MongoOperations mongoOperations;

	private Class<T> entityClass; // 实体的运行是类
	private Class<PK> pkClass; // 实体的运行是类
	private String collectionName;// 创建的数据表的名称是类名的首字母小写
	
	@SuppressWarnings("unchecked")
	public BaseMongoRepositoryImpl(MongoEntityInformation<T, PK> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		this.mongoOperations = mongoOperations;
		this.entityClass = MyBeanUtil.getSuperClassGenericType(this.getClass());
		this.pkClass = MyBeanUtil.getSuperClassGenericType(this.getClass(), 1);
		this.collectionName = _getCollectionName();
	}

	public Iterable<T> findList(Criteria criteria, int pageSize, int pageNumber, Direction sortDirection, String... sortFields) {
		return null;
	}

	public T findOne(Criteria criteria, Direction sortDirection, String... sortFields) {
		Query query = new Query(criteria);
		_buildSort(query, sortDirection, sortFields);
		return mongoOperations.findOne(query, entityClass, collectionName);
	}

	public boolean exists(Criteria criteria) {
		// TODO Auto-generated method stub
		return false;
	}

	public long count(Criteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean delete(Criteria criteria) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 根据类名获取集合的名称 :将类名首字母小写，转换为mongodb的集合名称 */
	private String _getCollectionName() {
		return StringUtils.uncapitalize(entityClass.getSimpleName());
	}

	private void _buildSort(Query query, Direction sortDirection, String... sortFields) {
		if (null == sortFields || null == sortDirection || sortFields.length == 0) {
			return;
		}
		query.with(new Sort(sortDirection, sortFields));
	}

}
