package org.springframework.data.solr.example;

import org.bson.Document;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class ITestMyWishListMongoRepository {

	@Autowired
	MongoTemplate mongoTemplate;


	@After
	public void tearDown() {
	}

	@Test
	public void testMongoTemplate() {
		System.out.println(mongoTemplate);
		
		
		String seq_name = "mywishList";
		String sequence_collection = "seq";
		String sequence_field = "seq";

		MongoCollection<Document> seq = this.mongoTemplate.getCollection(sequence_collection);
		Document update = new Document("$inc", new Document(sequence_field, Integer.valueOf(1)));
		FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions();
		findOneAndUpdateOptions.upsert(true);
		Document findOneAndUpdate = seq.findOneAndUpdate(Filters.eq("_id", seq_name), update, findOneAndUpdateOptions);

		String object = findOneAndUpdate.get(sequence_field).toString();
	}

	// public String getNextId(String seq_name) {
	// String sequence_collection = "seq";
	// String sequence_field = "seq";
	//
	// DBCollection seq = this.mongoTemplate.getCollection(sequence_collection);
	//
	// DBObject query = new BasicDBObject();
	// query.put("_id", seq_name);
	//
	// DBObject change = new BasicDBObject(sequence_field, Integer.valueOf(1));
	// DBObject update = new BasicDBObject("$inc", change);
	//
	// DBObject res = seq.findAndModify(query, new BasicDBObject(), new
	// BasicDBObject(), false, update, true, true);
	// return res.get(sequence_field).toString();
	// }
}
