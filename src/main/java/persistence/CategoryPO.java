package persistence;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ovchinnikov on 11.04.2016.
 */
@Entity
@Table(name = "category", schema = "", catalog = "java_task_blog")
public class CategoryPO {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 30)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Collection<PostPO> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryPO that = (CategoryPO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<PostPO> getPosts() {
        return posts;
    }

    public void setPosts(Collection<PostPO> posts) {
        this.posts = posts;
    }
}
