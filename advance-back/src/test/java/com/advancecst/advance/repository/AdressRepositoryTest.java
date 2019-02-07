package com.advancecst.advance.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.*;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.advancecst.advance.model.*;
import com.advancecst.advance.util.*;

@RunWith(Arquillian.class)
public class AdressRepositoryTest {
    @Inject
    private AdressRepository AdressRepository;

    @Inject
    private UserTransaction usrTrx;

    @Test(expected = Exception.class)
    public void findAdressInvalid() {
        // Find a Adress
        Adress AdressTrouve = AdressRepository.find(null);
        AdressTrouve.toString();// pour supprimer le warning
    }

    @Test(expected = Exception.class)
    public void createInvalidAdress() throws Exception {
        Adress Adress = new Adress(null, null, "12", "RUE DE LA PAIX", "75000", "PARIS", null);
        Adress = AdressRepository.create(Adress);
    }

    @Test
    public void create() throws Exception {
        assertEquals(Long.valueOf(0), AdressRepository.countAll());
        assertEquals(0, AdressRepository.findAll().size());

        Adress Adress = new Adress(AdressType.PERSO, null, "12", "RUE DE LA PAIX", "75000", "PARIS", null);
        Adress = AdressRepository.create(Adress);

        assertEquals(Long.valueOf(1), AdressRepository.countAll());

        // Adress ID
        assertNotNull(Adress.getId());

        // Find a Adress
        Adress AdressTrouvee = AdressRepository.find(Adress.getId());
        assertNotNull(AdressTrouvee);

        assertEquals(Long.valueOf(1), AdressRepository.countAll());
        assertEquals(1, AdressRepository.findAll().size());
    }

    @Test
    public void modify() throws Exception {
        usrTrx.begin();
        assertEquals(Long.valueOf(1), AdressRepository.countAll());
        assertEquals(1, AdressRepository.findAll().size());

        Adress Adress = new Adress(AdressType.PERSO, null, "12", "RUE DE LA PAIX", "75000", "PARIS", null);
        Adress = AdressRepository.create(Adress);// normalement lié à la base

        assertEquals(Long.valueOf(2), AdressRepository.countAll());

        // Adress ID
        assertNotNull(Adress.getId());

        // Modification de l'Adress
        Adress.setZipCode("93000");// vu que persisté, normalement lié à la base, donc on devrait avoir la mise à
                                   // jour
        // Adress = AdressRepository.update(Adress);// normalement lié à la base

        // Find a Adress
        Adress AdressTrouvee = AdressRepository.find(Adress.getId());
        assertNotNull(AdressTrouvee);

        assertEquals(Long.valueOf(2), AdressRepository.countAll());
        assertEquals(2, AdressRepository.findAll().size());

        assertEquals("93000", AdressTrouvee.getZipCode());

        // Modification de l'Adress
        Adress.setZipCode("88000");// vu que persisté, normalement lié à la base, donc on devrait avoir la mise à
                                   // jour
        AdressTrouvee = AdressRepository.find(Adress.getId());
        assertNotNull(AdressTrouvee);

        assertEquals(Long.valueOf(2), AdressRepository.countAll());
        assertEquals(2, AdressRepository.findAll().size());

        assertEquals("88000", AdressTrouvee.getZipCode());
        usrTrx.commit();
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(AdressRepository.class).addClass(Adress.class)
                .addClass(AdressType.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}