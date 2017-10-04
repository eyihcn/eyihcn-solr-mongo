package eyihcn.base.spring.data.solr.repository;

import java.io.Serializable;

import org.springframework.data.solr.repository.SolrCrudRepository;

import eyihcn.base.entity.BaseEntity;

public interface BaseSolrRepository <T extends BaseEntity<PK>, PK extends Serializable> extends SolrCrudRepository<T, PK> {

}
