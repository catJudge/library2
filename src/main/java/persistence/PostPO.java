package persistence;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by ovchinnikov on 11.04.2016.
 */
@Entity
@Table(name = "post", schema = "", catalog = "java_task_blog")
public class PostPO {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    @Basic
    @Column(name = "title", nullable = true, insertable = true, updatable = true, length = 100)
    private String title;
    @Basic
    @Column(name = "text", nullable = false, insertable = true, updatable = true, length = 2147483647)
    private String text;
    @Basic
    @Column(name = "created_date", nullable = false, insertable = true, updatable = true)
    private Timestamp createdDate;
    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 30)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "category_post", catalog = "java_task_blog", joinColumns = {
            @JoinColumn(name = "post_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id",
                    nullable = false, updatable = false)})
    private Collection<CategoryPO> categories;
    @OneToMany(mappedBy = "post")
    private Collection<CommentPO> comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

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
        if (createdDate != null ? !createdDate.equals(postPO.createdDate) : postPO.createdDate != null) return false;
        if (email != null ? !email.equals(postPO.email) : postPO.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Collection<CommentPO> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentPO> comments) {
        this.comments = comments;
    }
}
