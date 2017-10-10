package eyihcn.base.spring.data.solr.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.SolrTransactionSynchronizationAdapterBuilder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

public class SolrDao<T> {

	@Autowired
	SolrTemplate solrTemplate;

	public SolrTemplate getSolrTemplate() {
		return solrTemplate;
	}

	public void setSolrTemplate(SolrTemplate solrTemplate) {
		this.solrTemplate = solrTemplate;
	}

	public <S extends T> S save(S entity) {
		Assert.notNull(entity, "Cannot save 'null' entity.");
		registerTransactionSynchronisationIfSynchronisationActive();
		this.solrTemplate.saveBean(entity);
		commitIfTransactionSynchronisationIsInactive();
		return entity;
	}

	private void registerTransactionSynchronisationIfSynchronisationActive() {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			registerTransactionSynchronisationAdapter();
		}
	}

	private void registerTransactionSynchronisationAdapter() {
		TransactionSynchronizationManager.registerSynchronization(SolrTransactionSynchronizationAdapterBuilder.forOperations(this.solrTemplate).withDefaultBehaviour());
	}

	private void commitIfTransactionSynchronisationIsInactive() {
		if (!TransactionSynchronizationManager.isSynchronizationActive()) {
			this.solrTemplate.commit();
		}
	}
}
