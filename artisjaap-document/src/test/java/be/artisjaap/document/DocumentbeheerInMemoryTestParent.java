package be.artisjaap.document;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ContextConfiguration(classes = {
        DocumentbeheerApplication.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class DocumentbeheerInMemoryTestParent  {

    @Resource
    private MongoTemplate mongoTemplate;

    @Before
    public void initDB(){
        mongoTemplate.getCollectionNames().forEach(this::dropCollection);
    }

    private void dropCollection(String collection) {
        System.out.println("DROP COLLECTION " + collection);
        mongoTemplate.dropCollection(collection);

    }
}
