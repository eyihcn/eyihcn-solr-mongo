package eyihcn.base.spring.data.solr.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTransactionSynchronizationAdapterBuilder;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.repository.query.SolrEntityInformation;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.data.solr.repository.support.SolrEntityInformationCreatorImpl;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import eyihcn.base.entity.BaseEntity;

public class BaseSolrRepositoryImpl<T extends BaseEntity<PK>, PK extends Serializable> extends SimpleSolrRepository<T, PK> implements BaseSolrRepository<T, PK> {
	
	
	public BaseSolrRepositoryImpl(SolrEntityInformation<T, ?> metadata, SolrOperations solrOperations) {
		super(metadata, solrOperations);
	}
	
	public BaseSolrRepositoryImpl(SolrOperations solrOperations, Class<T> entityClass) {
		this(new SolrEntityInformationCreatorImpl(new SimpleSolrMappingContext()).getEntityInformation(entityClass), solrOperations);
	}

	@SuppressWarnings("unchecked")
	public <S extends Page<T>> S findList(Criteria criteria,  Pageable pageable) {

		Assert.notNull(criteria, "criteria can not be  'null'");

		return (S) getSolrOperations().queryForPage( new SimpleQuery(criteria, pageable), getEntityClass());
	}

	public T findOne(Criteria criteria,  Pageable pageable) {
		SimpleQuery query = new SimpleQuery(criteria);
		if (null != pageable) {
			query.setPageRequest(pageable);
		}
		return getSolrOperations().queryForObject(query,getEntityClass());
	}

	public T findOne(Criteria criteria,  Sort sort) {
		SimpleQuery query = new SimpleQuery(criteria);
		if (null != sort) {
			query.addSort(sort);
		}
		return getSolrOperations().queryForObject(query, getEntityClass());
	}

	public T findOne(Criteria criteria) {

		return findOne(criteria, (Sort) null);
	}

	public boolean exists(Criteria criteria,  Pageable pageable) {

		return this.findOne(criteria, pageable) != null;
	}

	public boolean exists(Criteria criteria,  Sort sort) {

		return this.findOne(criteria, sort) != null;
	}

	public boolean exists(Criteria criteria) {

		return exists(criteria, (Sort) null);
	}

	public long count(Criteria criteria,  Pageable pageable) {

		SimpleQuery query = new SimpleQuery(criteria);
		if (null != pageable) {
			query.setPageRequest(pageable);
		}
		return getSolrOperations().count(query);
	}

	public long count(Criteria criteria,  Sort sort) {

		SimpleQuery query = new SimpleQuery(criteria);
		if (null != sort) {
			query.addSort(sort);
		}
		return getSolrOperations().count( query);
	}

	public long count(Criteria criteria) {

		return count(criteria, (Sort) null);
	}

	public boolean delete(Criteria criteria,  Pageable pageable) {
		SimpleQuery query = new SimpleQuery(criteria);
		if (null != pageable) {
			query.setPageRequest(pageable);
		}
		registerTransactionSynchronisationIfSynchronisationActive();
		getSolrOperations().delete(query);
		commitIfTransactionSynchronisationIsInactive();
		return true;
	}

	public boolean delete(Criteria criteria,  Sort sort) {
		SimpleQuery query = new SimpleQuery(criteria);
		if (null != sort) {
			query.addSort(sort);
		}
		registerTransactionSynchronisationIfSynchronisationActive();
		getSolrOperations().delete(query);
		commitIfTransactionSynchronisationIsInactive();
		return true;
	}

	public boolean delete(Criteria criteria) {

		return delete(criteria, (Sort) null);
	}

	private void registerTransactionSynchronisationIfSynchronisationActive() {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			registerTransactionSynchronisationAdapter();
		}
	}

	private void registerTransactionSynchronisationAdapter() {
		TransactionSynchronizationManager.registerSynchronization(SolrTransactionSynchronizationAdapterBuilder
				.forOperations(this.getSolrOperations()).withDefaultBehaviour());
	}

	private void commitIfTransactionSynchronisationIsInactive() {
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			this.getSolrOperations().commit();
		}
	}

}
