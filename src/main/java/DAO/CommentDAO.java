package DAO;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.CommentPO;
import persistence.PostPO;

import java.util.ArrayList;
import java.util.List;


public class CommentDAO {
    public static Long insertComment(CommentPO commentPO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            session.save(commentPO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
        return commentPO.getId();
    }

    public static void deleteComment(CommentPO commentPO) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(commentPO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<CommentPO> getCommentsByPostId(Long pid){
        List<CommentPO> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from CommentPO where postId= " + pid ).list();
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
