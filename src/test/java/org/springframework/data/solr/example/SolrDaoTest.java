package org.springframework.data.solr.example;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eyihcn.base.spring.data.solr.repository.SolrDao;
import eyihcn.data.example.model.MyWishList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class SolrDaoTest extends AbstractSolrIntegrationTest {


	@Autowired
	SolrDao<MyWishList> solrDao;

	@Before
	public void setUpClass() {
		// solrDao.deleteAll();
		List<MyWishList> entities = createMyWishListList(10);
		System.out.println(".....createMyWishListList........");
		// repo.saveAll(entities);
	}


	@After
	public void tearDown() {
		// repo.deleteAll();
	}

	@Test
	public void testFindOne() {
		MyWishList createMyWishList = this.createMyWishList(1);
		MyWishList save = solrDao.save(createMyWishList);
		// Iterable<MyWishList> findAll = repo.findAll(new SolrPageRequest(0, 1,
		// Direction.DESC, SolrSearchableFields.PRICE.getName()));
	}

	// @Test
	// public void testDeleteAll() {
	// repo.deleteAll();
	// }
	//
	// @Test
	// public void testFindLike() {
	// MyWishList findByNameLike = repo.findOneByNameLike("MyWish");
	// // Page<MyWishList> findList =
	// //
	// repo.findList(Criteria.where(SolrSearchableFields.NAME.getName()).contains("Wish").and(Criteria.where(SolrSearchableFields.PRICE).is(200)),
	// // null);
	// }

	// @Test
	// public void testFind() {
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	// List<MyWishList> findByName =
	// repo.findListByName(entities.get(0).getName());
	// MyWishList findOne =
	// repo.findOne(Criteria.where(SolrSearchableFields.NAME.getName()).is(entities.get(0).getName()));
	// System.out.println(findOne);
	// }
	//
	// @Test
	// public void testDeleteByID() {
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	// repo.delete(Criteria.where(SolrSearchableFields.NAME.getName()).is(entities.get(0).getName()));
	// }
	//
	// @Test
	// public void testDelete() {
	// Assert.assertEquals(true,
	// repo.delete(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	// repo.delete(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200));
	// Assert.assertEquals(0,
	// repo.count(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	// }
	//
	// @Test
	// public void testCount() {
	// Assert.assertEquals(0,
	// repo.count(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	// Assert.assertEquals(3,
	// repo.count(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	// }
	//
	// @Test
	// public void testExsit() {
	// Assert.assertEquals(true,
	// repo.exists(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	// Assert.assertEquals(false,
	// repo.exists(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200)));
	// }
	//
	// @Test
	// public void testQuery() {
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	// System.out.println(repo.findListByName("MyWishList-1").get(0));
	//
	// Pageable pageable = new SolrPageRequest(0, 1);
	// Page<MyWishList> findAll = repo.findAll(pageable);
	// System.out.println("findAll.toString()" + findAll.toString());
	// System.out.println("findAll.getTotalPages()" + findAll.getTotalPages());
	//
	// pageable = new SolrPageRequest(0, 1, Direction.DESC,
	// SolrSearchableFields.PRICE.getName());
	// Page<MyWishList> page =
	// repo.findList(Criteria.where(SolrSearchableFields.PRICE.getName()).lessThanEqual(200),
	// pageable);
	//
	// }
	//
	// @Test
	// public void testCRUD() {
	//
	// Assert.assertEquals(0, repo.count());
	//
	// List<MyWishList> entities = createMyWishListList(10);
	// repo.saveAll(entities);
	//
	// Assert.assertEquals(entities.size(), repo.count());
	//
	// MyWishList myWishList = entities.get(0);
	//
	// Assert.assertEquals(myWishList.getName(),
	// repo.findById(myWishList.getId()).get().getName());
	// myWishList.setName("update-name");
	// repo.save(myWishList);
	// Assert.assertEquals("update-name",
	// repo.findById(myWishList.getId()).get().getName());
	// }
}
