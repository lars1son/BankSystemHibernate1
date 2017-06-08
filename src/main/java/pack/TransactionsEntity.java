package pack;

import javax.persistence.*;

/**
 * Created by Артем on 05.06.2017.
 */
@Entity
@Table(name = "transactions")
public class TransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String operation;

    public TransactionsEntity(String operation) {
        this.operation = operation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountsEntity accountId;

    public TransactionsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public AccountsEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountsEntity accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionsEntity that = (TransactionsEntity) o;

        if (id != that.id) return false;
        if (operation != null ? !operation.equals(that.operation) : that.operation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }


}
