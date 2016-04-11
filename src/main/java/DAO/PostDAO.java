package DAO;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.CommentPO;
import persistence.PostPO;

import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    public static Long insertPost(PostPO postPO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(postPO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
        return postPO.getId();
    }

    public static void deletePost(PostPO postPO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(postPO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<PostPO> getAllPosts() {
        List<PostPO> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from PostPO order by createdDate desc ").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    public static List<PostPO> getPostsByCategoryId(Long cid){
        List<PostPO> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from PostPO where categoryId= " + cid ).list();
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
