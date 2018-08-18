package hibernate.lesson3.repositories;

import hibernate.lesson3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;

public class GeneralDAO<T> {
    private Class<T> typeParameterClass;

    public void setTypeParameterClass(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T save(T obj) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(obj);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Saving is failed");
            System.err.println(e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Object was not saved.\n" + e.getMessage());
        } finally {
            HibernateUtil.shutDownSessionFactory();
        }

        return obj;
    }

    public void delete(long id) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(typeParameterClass, id));

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Deleting is failed");
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Object " + id + " was not found");
        } finally {
            HibernateUtil.shutDownSessionFactory();
        }
    }

    public void update(T obj) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(obj);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Updating is failed");
            System.err.println(e.getMessage());
        } catch (PersistenceException e) {
            System.err.println("Object was not found or data is wrong");
        } finally {
            HibernateUtil.shutDownSessionFactory();
        }
    }

    public T findById(long id) {
        try (Session session = HibernateUtil.createSessionFactory().openSession()) {

//            System.out.println(session.get(typeParameterClass, id).toString());
            return session.get(typeParameterClass, id);

        } catch (HibernateException e) {
            System.err.println("Searching is failed");
            System.err.println(e.getMessage());
        } finally {
            HibernateUtil.shutDownSessionFactory();
        }

        return null;
    }
}
