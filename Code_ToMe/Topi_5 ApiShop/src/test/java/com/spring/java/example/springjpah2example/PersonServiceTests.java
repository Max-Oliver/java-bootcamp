package com.spring.java.example.springjpah2example;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.client.api.main.Person;
import com.client.api.main.PersonJpaRepository;
import com.client.api.main.PersonService;

import java.util.Optional;

public class PersonServiceTests {

    private static final Long TEST_ID = 1L;
    private static final String TEST_FIRST_NAME = "TEST_FIRST_NAME";
    private static final String TEST_LAST_NAME = "TEST_LAST_NAME";

    private PersonJpaRepository mockRepository = Mockito.mock(PersonJpaRepository.class);

    private PersonService testService;
    private Person testPerson;

  //  @Before
    public void setUpPerson() { 
        testPerson = new Person();
        testPerson.setId(TEST_ID);
        testPerson.setFirstName(TEST_FIRST_NAME);
        testPerson.setLastName(TEST_LAST_NAME);
    }

   // @Test
    public void findPersonById_IdInRepository_PersonIsReturned() {
        //prepare
        Mockito.when(mockRepository.findById(TEST_ID)).thenReturn(Optional.of(testPerson));
        testService = new PersonService(mockRepository);
        //execute
        final Person testValue = testService.findPersonById(1L);
        //assert
        Assertions.assertThat(testValue).isNotNull();
        Assertions.assertThat(testValue.getFirstName()).isEqualTo(TEST_FIRST_NAME);
    }

//    @Test
    public void findPersonById_IdNotInRepository_NullIsReturned() {
        //prepare
        Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        testService = new PersonService(mockRepository);
        //execute
        final Person testValue = testService.findPersonById(1L);
        //assert
        Assertions.assertThat(testValue).isNull();
    }
}
