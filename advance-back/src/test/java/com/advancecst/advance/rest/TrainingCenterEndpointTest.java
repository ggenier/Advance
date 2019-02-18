package com.advancecst.advance.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.advancecst.advance.model.*;
import com.advancecst.advance.repository.EmployeeRepository;
import com.advancecst.advance.repository.TrainingCenterRepository;
import com.advancecst.advance.util.*;

@RunWith(Arquillian.class)
@RunAsClient
public class TrainingCenterEndpointTest {

   @Test
   public void countTrainingCenter(@ArquillianResteasyResource("api/trainingcenter") WebTarget webTarget)
         throws Exception {
      Response response = webTarget.path("countTrainingCenter").request().get();
      // assertEquals(Long.valueOf(0), response.readEntity(Long.class));
      // assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
      // response.getStatus());
   }

   @Test
   public void getAllTrainingCenters(@ArquillianResteasyResource("api/trainingcenter") WebTarget webTarget)
         throws Exception {
      Response response = webTarget.path("getAllTrainingCenters").request().get();
      // assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
      // response.getStatus());
   }

   @Test
   public void createTrainingCenter(@ArquillianResteasyResource("api/trainingcenter") WebTarget webTarget)
         throws Exception {

      // Creation Adress
      Adress Adress = new Adress(AdressType.PERSO, "2", "ICI", "LA", "75000", "TEST", null);
      Set<Adress> adresses = new HashSet<Adress>();
      adresses.add(Adress);

      // Creation Téléphone
      Phone phone = new Phone(PhoneType.PERSO, "0606060606");
      Set<Phone> phones = new HashSet<Phone>();
      phones.add(phone);

      // Creation Identification
      Identification identification = new Identification(adresses, phones);

      TrainingCenter trainingCenter = new TrainingCenter(identification, "CENTRE FORMATION HUISSERIE");
      Response response = webTarget.path("createTrainingCenter").request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(trainingCenter, MediaType.APPLICATION_JSON));
      // assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());

      // String location = response.getHeaderString("location");
      // assertNotNull(location);
      // String trainingCenterId = location.substring(location.lastIndexOf("/") + 1);

      // assertEquals("1", trainingCenterId);

   }

   @Deployment
   // @OverProtocol("Servlet 3.0")
   public static Archive<?> createDeploymentPackage() {
      return ShrinkWrap.create(WebArchive.class).addClass(TrainingCenterRepository.class).addClass(Employee.class)
            .addClass(TransfoDates.class).addClass(LocalDateAdapter.class).addClass(Person.class)
            .addClass(EmployeeType.class).addClass(Adress.class).addClass(Phone.class).addClass(Identification.class)
            .addClass(Skill.class).addClass(Training.class).addClass(TrainingCenter.class).addClass(AdressType.class)
            .addClass(PhoneType.class).addClass(SkillType.class).addClass(TrainingType.class)
            .addClass(EmployeeEndpoint.class).addClass(EmployeeRepository.class).addClass(JAXRSConfiguration.class)
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml");
   }

}
