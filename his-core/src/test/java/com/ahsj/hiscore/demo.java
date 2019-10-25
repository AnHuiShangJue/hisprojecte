import com.ahsj.hiscore.controller.ward.HisWardsController;
import com.ahsj.hiscore.entity.HisWard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@SpringBootTest(classes = HisWardsController.class)

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

public class demo {

    @Resource

    private HisWardsController hisWardsController;


    @Test

    public void testIndex() throws Exception {

        /*TestCase.assertEquals(this.sampleController.index(),

                "Greetings from Spring Boot!");*/
        List<HisWard> wardAll = hisWardsController.getWardAll();
        for (HisWard hisWard : wardAll) {
            System.out.println(hisWard);
        }

    }

}