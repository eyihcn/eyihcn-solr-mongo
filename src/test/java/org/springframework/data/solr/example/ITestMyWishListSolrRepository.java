package org.springframework.data.solr.example;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.example.repository.SolrMyWishListRepository;
import org.springframework.data.solr.example.repository.SolrSearchableFields;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eyihcn.data.example.model.MyWishList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class ITestMyWishListSolrRepository extends AbstractSolrIntegrationTest {

	@Autowired
	SolrMyWishListRepository repo;

	List<MyWishList> entities = null;

	@Before
	public void setUpClass() {
		repo.deleteAll();
		entities = createMyWishListList(10);
		System.out.println(".....createMyWishListList........");
		repo.save(entities);
	}

	// @AfterClass
	// public void tearDownClass() {
	// // repo.deleteAll();
	// }

	@After
	public void tearDown() {
		// repo.deleteAll();
	}

	@Test
	public void testDeleteAll() {
		repo.deleteAll();
	}

	@Test
	public void testFindLike() {
		MyWishList findByNameLike = repo.findOneByNameLike("MyWish");
		Page<MyWishList> findList = repo.findList(Criteria.where(SolrSearchableFields.NAME.getName()).contains("Wish").and(Criteria.where(SolrSearchableFields.PRICE).is(200)), null);
	}

	@Test
	public void testFind() {

		String name = entities.get(0).getName();
		List<MyWishList> findByName = repo.findListByName(name);
		Assert.assertEquals(findByName.get(0).getName(), name);

		MyWishList findOne = repo.findOne(Criteria.where(SolrSearchableFields.NAME.getName()).is(name));
		Assert.assertEquals(findOne.getName(), name);
	}

	@Test
	public void testDeleteByID() {

		Integer id = entities.get(0).getId();
		repo.delete(id);
		Assert.assertNull(repo.findById(id));
	}

	@Test
	public void testDelete() {
		Assert.assertEquals(true, repo.delete(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
		List<MyWishList> entities = createMyWishListList(10);
		repo.save(entities);
		repo.delete(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200));
		Assert.assertEquals(0, repo.count(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	}

	@Test
	public void testCount() {
		Assert.assertEquals(3, repo.count(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
		List<MyWishList> entities = createMyWishListList(10);
		repo.save(entities);
		repo.delete(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200));
		Assert.assertEquals(0, repo.count(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	}

	@Test
	public void testExsit() {
		Assert.assertEquals(true, repo.exists(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
		List<MyWishList> entities = createMyWishListList(10);
		repo.save(entities);
		repo.delete(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200));
		Assert.assertEquals(false, repo.exists(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	}

	@Test
	public void testQuery() {
		List<MyWishList> entities = createMyWishListList(10);
		repo.save(entities);
		System.out.println(repo.findListByName("MyWishList|1").get(0));

		Pageable pageable = new SolrPageRequest(0, 1);
		Page<MyWishList> findAll = repo.findAll(pageable);
		System.out.println(findAll.getContent().size());
	}

	@Test
	public void testCRUD() {

		List<MyWishList> entities = createMyWishListList(10);
		repo.save(entities);

		Assert.assertEquals(entities.size(), repo.count());

		MyWishList myWishList = entities.get(0);

		Assert.assertEquals(myWishList.getName(), repo.findById(myWishList.getId()).getName());
		myWishList.setName("update-name");
		repo.save(myWishList);
		Assert.assertEquals("update-name", repo.findById(myWishList.getId()).getName());
	}
}
