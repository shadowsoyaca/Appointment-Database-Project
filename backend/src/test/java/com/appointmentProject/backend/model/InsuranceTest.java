package com.appointmentProject.backend.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.appointmentProject.backend.model.Insurance;

/***************************************************************************
 *   InsuranceTest.java
 *
 *          This will test the Construction, getters, setters, and
 *          toString of the Insurance model.
 *          Prioritizes data integrity over logic in these tests.
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/05/2025
 ****************************************************************************/
public class InsuranceTest {

    @Test
    void testGettersAndSetters(){
        //Test Objects
        String id1 = "IN23050708", id2 = "IN00000009", id3 = "IN11111111", id4 = "IN22222222";
        String insuranceName1 = "Medicade",insuranceName2 = "BlueCross BlueShield", insuranceName3 = "UnitedHealthcare";
        String phone1 = "4140023400", phone2 = "2620107798", phone3 = "8882054567";
        String email1 = "medicade@gmail.com", email2 = "CrossShield@uwm.edu", email3 = "uHealth@yahoo.org";
        String address1 = "14360 Milestone Ln", address2 = "23070 Charles St", address3 = "92000 Hill Ct", address4 = "2444 S Luke Lane";

        Insurance ins1 = new Insurance(id1, insuranceName1, phone1, email1, address1);
        Insurance ins2 = new Insurance(id2, insuranceName2, phone2, email2, address2);
        Insurance ins3 = new Insurance(id3, insuranceName3, phone3, email3, address3);
        assertAll(
                //id getter
            () -> assertEquals(id1, ins1.getId()),
            () -> assertEquals(id2, ins2.getId()),
            () -> assertEquals(id3, ins3.getId()),
                //id Setter
            () -> ins1.setId(id4),
            () -> assertEquals(id4, ins1.getId()),
                //Insurance Name Getter
            () -> assertEquals(insuranceName1,ins1.getInsurance_name()),
            () -> assertEquals(insuranceName2,ins2.getInsurance_name()),
            () -> assertEquals(insuranceName3,ins3.getInsurance_name()),
                //Insurance Name Setter
            () ->ins2.setInsurance_name(insuranceName3),
            () -> assertEquals(insuranceName3, ins2.getInsurance_name()),
                //phone getter
            () -> assertEquals(phone1, ins1.getPhone()),
            () -> assertEquals(phone2, ins2.getPhone()),
            () -> assertEquals(phone3, ins3.getPhone()),
                //phone setter
            () -> ins3.setPhone(phone2),
            () -> assertEquals(phone2, ins3.getPhone()),
                //email getter
            () -> assertEquals(email1, ins1.getEmail()),
            () -> assertEquals(email2, ins2.getEmail()),
            () -> assertEquals(email3, ins3.getEmail()),
                //email setter
            () -> ins2.setEmail(email3),
            () -> assertEquals(email3, ins2.getEmail()),
                //address getter
            () -> assertEquals(address1, ins1.getAddress()),
            () -> assertEquals(address2, ins2.getAddress()),
            () -> assertEquals(address3, ins3.getAddress()),
                //address setter
            () -> ins2.setAddress(address4),
            () -> assertEquals(address4, ins2.getAddress())
        );
    }

    @Test
    void testToString(){
        //Test Object
        Insurance ins = new Insurance
                ("IN54678000","Aetna", "4140093359",
                        "recep@Aetna.org", "4439 W Talent LN");
        String expected =
                "Insurance:\n"+
                "\nIN id: IN54678000"+
                "\ninsurance_name: Aetna"+
                "\nphone: 4140093359"+
                "\nemail: recep@Aetna.org"+
                "\naddress: 4439 W Talent LN"+
                "\n";
        assertEquals(expected, ins.toString());


    }
}
