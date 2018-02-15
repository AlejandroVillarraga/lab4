/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cosw.jpa.sample;

import edu.eci.cosw.jpa.sample.model.Curso;
import edu.eci.cosw.jpa.sample.model.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author hcadavid
 */
public class SimpleMainApp {
   
    public static void main(String a[]){
        SessionFactory sf=getSessionFactory();
        Session s=sf.openSession();
        Transaction tx=s.beginTransaction();
        
        Estudiante e1 = new Estudiante(2105534, "Carlos"); //(Cliente) s.load(Cliente.class, new ClienteId(1,"cc"));
        Estudiante e2 = new Estudiante(2105461, "Jhordana");
        //p.getConsultas().add(new Consulta(new Date(2018),"Carlos sanchez agregando tresdsfdsfsf"));
        Curso c1 = new Curso(5678, "Programacion orientada a objetos", "POOB");
        Curso c2 = new Curso(5679, "Arquitectura de S", "ARSW");
        c1.getEstudiantes().add(e1);
        c1.getEstudiantes().add(e2);
        c2.getEstudiantes().add(e1);
        c2.getEstudiantes().add(e2);
        e1.getCursos().add(c1);
        e1.getCursos().add(c2);
        e2.getCursos().add(c1);
        e2.getCursos().add(c2);
        s.saveOrUpdate(e1);
        s.saveOrUpdate(e2);
        s.saveOrUpdate(c1);
        s.saveOrUpdate(c2);
        
        //Estudiante carlos=(Estudiante)s.load(Estudiante.class, 2105534);
        //System.out.println("-----------------------CODIGO---------------------");
        //System.out.println(((Curso)carlos.getCursos().toArray()[1]).getEstudiantes().size());
        
        
        
        tx.commit(); 
        s.close();
        sf.close();
    }

    public static SessionFactory getSessionFactory() {
        // loads configuration and mappings
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

}
