package persistence;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ovchinnikov on 10.04.2016.
 */
@Entity
@Table(name = "post", schema = "", catalog = "java_task_blog")
public class PostPO {
    private Long id;
    private String title;
    private String text;
    private Long categoryId;
    private Timestamp createdDate;
    private String email;

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
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "text", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "created_date", nullable = false, insertable = true, updatable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostPO postPO = (PostPO) o;

        if (id != null ? !id.equals(postPO.id) : postPO.id != null) return false;
        if (title != null ? !title.equals(postPO.title) : postPO.title != null) return false;
        if (text != null ? !text.equals(postPO.text) : postPO.text != null) return false;
        if (categoryId != null ? !categoryId.equals(postPO.categoryId) : postPO.categoryId != null) return false;
        if (createdDate != null ? !createdDate.equals(postPO.createdDate) : postPO.createdDate != null) return false;
        if (email != null ? !email.equals(postPO.email) : postPO.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
