package eyihcn.base.spring.data.mongo.support;

import java.io.Serializable;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.support.SolrRepositoryFactory;
import org.springframework.data.solr.repository.support.SolrRepositoryFactoryBean;
import org.springframework.data.solr.server.support.SolrClientUtils;

import eyihcn.base.entity.BaseEntity;
import eyihcn.base.spring.data.solr.repository.BaseSolrRepositoryImpl;

public class CustomSolrRepositoryFactoryBean<T extends Repository<S, ID>, S extends BaseEntity<ID>, ID extends Serializable> extends SolrRepositoryFactoryBean<T, S, ID> {


	@Override
	protected RepositoryFactorySupport doCreateRepositoryFactory() {
		return new CustomSolrRepositoryFactory(getSolrOperations());
	}

	private static class CustomSolrRepositoryFactory<T extends BaseEntity<ID>, ID extends Serializable> extends SolrRepositoryFactory {

		private  SolrOperations solrOperations;

		public CustomSolrRepositoryFactory(SolrClient solrClient) {
			super(solrClient);
		}
		
		public CustomSolrRepositoryFactory(SolrOperations solrOperations) {
			super(solrOperations);
			this.solrOperations = solrOperations;
		}

		@Override
		protected Object getTargetRepository(RepositoryInformation metadata) {
			String resolveSolrCoreName = SolrClientUtils.resolveSolrCoreName(metadata.getDomainType());
			((SolrTemplate) solrOperations).setSolrCore(resolveSolrCoreName);
			return new BaseSolrRepositoryImpl<T, ID>(solrOperations, (Class<T>) metadata.getDomainType());
			// return super.getTargetRepository(metadata);
		}


		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseSolrRepositoryImpl.class;
		}
	}
}
