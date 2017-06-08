package pack;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "accounts")
public class AccountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity courseId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity usersId;
    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL)
    private List<TransactionsEntity> transactionssById = new ArrayList<TransactionsEntity>();
    private int number;
    @Column(name = "cash")
    private int cash;

    public int getCash() {
        return cash;
    }
    public void replenish(int cash){
        setCash(getCash()+cash);
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public AccountsEntity() {
    }

    public CourseEntity getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseEntity courseId) {
        this.courseId = courseId;
    }

    public List<TransactionsEntity> getTransactionssById() {
        return transactionssById;
    }

    public void setTransactionssById(List<TransactionsEntity> transactionssById) {
        this.transactionssById = transactionssById;
    }

    public UsersEntity getUsersId() {
        return usersId;
    }

    public void setUsersId(UsersEntity usersId) {
        this.usersId = usersId;
    }
    public void setTransaction(TransactionsEntity tran){
        tran.setAccountId(this);
        transactionssById.add(tran);
    }

    public AccountsEntity(int number) {

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
