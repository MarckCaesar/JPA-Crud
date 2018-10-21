package com;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* @author 20151627 */
public class Main {

    
    public static void main(String[] args) {
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("JPA_CRUDPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
       
        Employee e = new Employee();
        e.setId(11);
        e.setName("Pedro");
        e.setSurname("Gonz");
        
        Read(em);

        //Create(em, e);

        Update(em, e);

        //Delete(em, e);   
    }
    public static void Read(EntityManager em){
       List<Employee> list = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        for(Employee ee : list){
            System.out.print("ID: " + ee.getId());
            System.out.print("\nName: " + ee.getName());
            System.out.print("\nSurname: " + ee.getSurname()+"\n");
            
        } 
    }
     public static void Create(EntityManager em, Employee e){
        System.out.print("\nCreando Tabla\n"); 
        em.persist(e); 
        em.getTransaction().commit();
        System.out.print("\nTabla Creada\n");
        Read(em);
     }
     public static void Update(EntityManager em, Employee e){
        System.out.print("\nActualizando Tabla\n");
        em.merge(e);
        em.getTransaction().commit();
        System.out.print("\nTabla Actualizada\n");
        Read(em);
     }
    public static void Delete(EntityManager em, Employee ee){
        System.out.print("\nEliminando Tabla\n");
        Employee e = em.find(Employee.class, ee.getId());
        em.remove(e);
        em.getTransaction().commit();
        System.out.print("\nTabla Eliminada\n");
        Read(em);
    }
    
}
