package DAO;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.CategoryPO;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static Long insertCategory(CategoryPO categoryPO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(categoryPO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
        return categoryPO.getId();
    }

    public static void deleteCategory(CategoryPO categoryPO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(categoryPO
            );
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<CategoryPO> getAllCategory(){
        List<CategoryPO> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from CategoryPO").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

}
