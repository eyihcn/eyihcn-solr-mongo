package eyihcn.base.spring.data.solr.repository;

import java.io.Serializable;

import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.repository.query.SolrEntityInformation;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.data.solr.repository.support.SolrEntityInformationCreatorImpl;

import eyihcn.base.entity.BaseEntity;

public class BaseSolrRepositoryImpl <T extends BaseEntity<PK>, PK extends Serializable> extends SimpleSolrRepository<T, PK> {
	
	private static final String DEFAULT_ID_FIELD = "id";
	private String idFieldName = DEFAULT_ID_FIELD;
	private final SolrOperations solrOperations;
	private final Class<T> entityClass;
	private final String solrCollectionName;
	
	public BaseSolrRepositoryImpl(SolrOperations solrOperations, SolrEntityInformation<T, ?> metadata) {
		super(solrOperations, metadata);
		this.solrOperations = solrOperations;
		this.entityClass = metadata.getJavaType();
		this.idFieldName = metadata.getIdAttribute();
		this.solrCollectionName = metadata.getCollectionName();
	}
	
	public BaseSolrRepositoryImpl(SolrOperations solrOperations, Class<T> entityClass) {
		this(solrOperations, new SolrEntityInformationCreatorImpl(new SimpleSolrMappingContext()).getEntityInformation(entityClass));
	}
	
	

}
