package com.example.hotdealnoti.common.util;

import com.example.hotdealnoti.common.util.coupang.CoupangPartnersLinkGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoupangPartnersLinkGeneratorTest {

    @Autowired
    private CoupangPartnersLinkGenerator coupangPartnersLinkGenerator;
    @Test
    void generateLink() {
        try {
            String generateLink = coupangPartnersLinkGenerator.generateLink("https://www.coupang.com/vp/products/6148872544?vendorItemId=79127246450&from=home_CartProduct&traid=home_CartProduct&isAddedCart=");
            System.out.println(generateLink);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}