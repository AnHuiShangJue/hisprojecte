package com.ahsj.userinfor;

import com.ahsj.userinfor.entity.Organization;
import com.ahsj.userinfor.services.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInforApplicationTests {

    @Autowired
    OrganizationService organizationService;

    @Test
    public void contextLoads() throws Exception {
        Organization organization = new Organization();
        organization.setName("rwttt");
        organizationService.saveOrUpdate(organization, "2");
    }

}
