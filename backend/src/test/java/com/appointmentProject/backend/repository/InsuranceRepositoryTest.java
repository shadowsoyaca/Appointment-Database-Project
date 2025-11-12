package com.appointmentProject.backend.repository;

import com.appointmentProject.backend.model.Insurance;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*************************************************************************************************
 *  InsuranceRepositoryTest.java
 *
 *      The purpose of this test is to ensure that the Insurance Repository executes the queries
 *      correctly. @Autowired is used so that the object (or in this case the repository) is created
 *      and injected into the test so a new instance of the repository is not created every time the
 *      test is run. @Transactional must be included as operating queues with INSERT, UPDATE, or
 *      DELETE are required to be run inside a transaction (not done because of beforeEach). @BeforeEach
 *      instructs the test to reset the database after the completion of a test (@test).
 *
 *      The procedure of completing the test follows:
 *      1. Creating an Insurance object
 *      2. Calling the Custom Query
 *      3. Assert the query operated successfully
 *
 * @author Matthew Kiyono
 * @version 1.0
 * @since 11/12/2025
 ************************************************************************************************/
@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InsuranceRepositoryTest {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void resetDatabase() {
        entityManager.createQuery("DELETE FROM Insurance").executeUpdate();
    }
    @Test
    void testFindByInsuranceName(){
        //Creation of Entity
        Insurance a = new Insurance(1, "StateFarm", "3322030045",
                "jake@statefarm.org", "123 Farmhand Ave");
        Insurance b = new Insurance(2, "Blue Cross", "332220945",
                "blue@cross.org", "3399 Shield Ln");

        insuranceRepository.save(a);
        insuranceRepository.save(b);


        //Calling the Query
        List<Insurance> results =  insuranceRepository.findByInsuranceName("StateFarm");

        //Asserting the Query
        assertNotNull(results, "Results should not be null");
        assertEquals(1, results.size(), "Size should be no bigger than 1");

        for (Insurance in : results) {
            assertEquals("StateFarm", in.getInsurance_name(), "Returned Insurance name should be StateFarm ");
        }
    }

    @Test
    void testFindByPhone(){
        //Creation of Entity
        Insurance a = new Insurance(1, "StateFarm", "3322030045",
                "jake@statefarm.org", "123 Farmhand Ave");
        Insurance b = new Insurance(2, "Blue Cross", "332220945",
                "blue@cross.org", "3399 Shield Ln");

        insuranceRepository.save(a);
        insuranceRepository.save(b);

        //Calling the Query
        List<Insurance> results =  insuranceRepository.findByPhone("332220945");

        //Assert Results
        assertNotNull(results, "Results should not be null");
        assertEquals(1, results.size(), "Size should be equal to 1.");

        for (Insurance in : results) {
            assertEquals("332220945", in.getPhone(), "Returned Phone number should be 332220945");
        }
    }

    @Test
    void testFindByEmail(){
        //Creation of Entity
        Insurance a = new Insurance(1, "StateFarm", "3322030045",
                "jake@statefarm.org", "123 Farmhand Ave");
        Insurance b = new Insurance(2, "Blue Cross", "332220945",
                "blue@cross.org", "3399 Shield Ln");

        insuranceRepository.save(a);
        insuranceRepository.save(b);

        //Calling the query
        List<Insurance> results = insuranceRepository.findByEmail("jake@statefarm.org");

        //Assert Results
        assertNotNull(results, "Results should not be null");
        assertEquals(1, results.size(), "Size should be equal to 1.");
        for (Insurance in : results) {
            assertEquals("jake@statefarm.org", in.getEmail(), "Email should be jake@statefarm.org.");
        }
    }

    @Test
    void testFindByAddress(){
        //Creation of Entity
        Insurance a = new Insurance(1, "StateFarm", "3322030045",
                "jake@statefarm.org", "123 Farmhand Ave");
        Insurance b = new Insurance(2, "Blue Cross", "332220945",
                "blue@cross.org", "3399 Shield Ln");

        insuranceRepository.save(a);
        insuranceRepository.save(b);

        //Calling the query
        List<Insurance> results = insuranceRepository.findByAddress("3399 Shield Ln");

        //Assert Results
        assertNotNull(results, "Results should not be null");
        assertEquals(1, results.size(), "Size should be equal to 1.");
        for (Insurance in : results) {
            assertEquals("3399 Shield Ln",  in.getAddress(), "Address should be 3399 Shield Ln");
        }
    }
}
