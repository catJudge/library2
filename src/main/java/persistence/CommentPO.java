package persistence;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ovchinnikov on 10.04.2016.
 */
@Entity
@Table(name = "comment", schema = "", catalog = "java_task_blog")
public class CommentPO {
    private Long id;
    private String text;
    private Long postId;
    private Timestamp createdDate;

    @GeneratedValue
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text", nullable = false, insertable = true, updatable = true, length = 300)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "post_id", nullable = false, insertable = true, updatable = true)
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "created_date", nullable = false, insertable = true, updatable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentPO commentPO = (CommentPO) o;

        if (id != null ? !id.equals(commentPO.id) : commentPO.id != null) return false;
        if (text != null ? !text.equals(commentPO.text) : commentPO.text != null) return false;
        if (postId != null ? !postId.equals(commentPO.postId) : commentPO.postId != null) return false;
        if (createdDate != null ? !createdDate.equals(commentPO.createdDate) : commentPO.createdDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
