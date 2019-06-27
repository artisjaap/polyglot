package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.AngularGeneratorApplication;
import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { AngularGeneratorApplication.class})
public class CreateNgPojoClassTest {

    @Autowired
    private CreateNgServiceMethod createNgServiceMethod;

    @Test
    public void testPojoGenerator(){
        String result = createNgServiceMethod.forMethod(ServiceMethodToMother.init());
        System.out.println(result);
        Assert.assertThat(1, CoreMatchers.is(1));
    }


}