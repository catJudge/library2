package DAO;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.CategoryPO;
import persistence.PostPO;

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

    public static List<CategoryPO> getAllCategory() {
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

    public static List<PostPO> getPostsByCategoryId(Long cid) {
        List<PostPO> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("select C.posts from CategoryPO C where C.id= :cid").setParameter("cid",cid).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    public static CategoryPO getCategoryById(Long cid) {
        CategoryPO categoryPO = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            categoryPO = (CategoryPO) session.get(CategoryPO.class, cid);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return categoryPO;
    }
}
