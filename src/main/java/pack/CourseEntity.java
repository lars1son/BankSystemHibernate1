package pack;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Артем on 05.06.2017.
 */
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int price;
    @OneToMany(mappedBy = "courseId",cascade = CascadeType.ALL)
    private List<AccountsEntity> accountssById = new ArrayList<AccountsEntity>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    public CourseEntity() {
    }

    @Override
    public int hashCode() {
        int result = id;

        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    public Collection<AccountsEntity> getAccountssById() {
        return accountssById;
    }

    public void setAccountssById(List<AccountsEntity> accountssById) {
        this.accountssById = accountssById;
    }
}
