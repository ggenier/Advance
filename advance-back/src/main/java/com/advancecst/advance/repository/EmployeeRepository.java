package com.advancecst.advance.repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.advancecst.advance.model.Employee;

import java.util.List;

@Transactional(TxType.SUPPORTS)
public class EmployeeRepository {
    @PersistenceContext(unitName = "AdvancePU")
    private EntityManager em;

    /* Recherche d'un employee par son id technique */
    public Employee findByTechnicalId(@NotNull Long id) {
        return this.em.find(Employee.class, id);
    }

    /* Recherche un salarié à partir de son identifiant RH */
    public Employee findByHRId(@NotNull String idEmployee) {

        TypedQuery<Employee> req = this.em.createNamedQuery("findByHRId", Employee.class).setParameter("idEmployee",
                idEmployee);

        Employee employee;
        try {
            employee = req.getSingleResult();
        } catch (NoResultException nre) {
            employee = null;
        }

        return employee;
    }

    @Transactional(TxType.REQUIRED)
    public Employee create(@NotNull Employee employee) {
        // Quand on crée l'objet via json, l'idEmployee est mis dans id
        if (employee.getId() != null)
            employee.setIdEmployee(employee.getId().toString());

        this.em.persist(employee);
        // Calcul de l'idSalarié si on a pas reçu
        if (employee.getIdEmployee().length() == 0)
            employee.setIdEmployee("SAL" + employee.getId().toString());

        return employee;
    }

    /* Suppression d'un employee par son id technique */
    @Transactional(TxType.REQUIRED)
    public void deleteByTechnicalId(@NotNull Long id) {
        this.em.remove(em.getReference(Employee.class, id));
    }

    /* Suppression d'un employee à partir de son identifant RH */
    @Transactional(TxType.REQUIRED)
    public void deleteByHRId(@NotNull String idEmployee) {
        Employee employee = this.findByHRId(idEmployee);

        if (employee != null)
            this.em.remove(this.em.getReference(Employee.class, employee.getId()));
    }

    public List<Employee> findAll() {
        TypedQuery<Employee> req = this.em.createQuery("SELECT s FROM Employee s ORDER BY id", Employee.class);
        return req.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> req = this.em.createQuery("SELECT count(s) FROM Employee s", Long.class);
        return req.getSingleResult();
    }
}