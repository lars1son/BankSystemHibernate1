package pack;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Артем on 05.06.2017.
 */
@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "usersId", cascade = CascadeType.ALL)
    private List<AccountsEntity> accountssById= new ArrayList<AccountsEntity>();

    public List<AccountsEntity> getAccountssById() {
        return accountssById;
    }

    public void setAccountssById(List<AccountsEntity> accountssById) {
        this.accountssById = accountssById;
    }

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

    public void addAccount(AccountsEntity account){
        account.setUsersId(this);
        accountssById.add(account);
    }
    public UsersEntity() {

    }

    public UsersEntity(String name) {

        this.name = name;

    }
}
