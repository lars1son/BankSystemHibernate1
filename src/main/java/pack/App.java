package pack;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        emf = Persistence.createEntityManagerFactory("JPATest");
        em = emf.createEntityManager();
        while (true) {
            System.out.println("1. Add bank user");
            System.out.println("2. Log in");
            switch (sc.nextInt()) {
                case 1:
                    addUser(sc);
                    break;
                case 2:
                    logIn(sc);

            }
        }
    }

    public static void addUser(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.next();
        UsersEntity user = new UsersEntity(name);
        System.out.print("Account number: ");
        int num = sc.nextInt();
        AccountsEntity acc = new AccountsEntity(num);
        acc.setCash(0);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  hh:mm:ss");
        TransactionsEntity trans = new TransactionsEntity("Account is created [" + sdf.format(date) + "]");
        acc.setTransaction(trans);
        user.addAccount(acc);
        em.persist(user);
        System.out.println("New bank-user is added!");

    }

    public static void logIn(Scanner sc) {
        while (true) {
            Boolean flag = false;
            System.out.println("1. Set name ");
            System.out.println("2. Back");
            switch (sc.nextInt()) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.next();
                    try {
                        Query query = em.createQuery("Select c from UsersEntity c where c.name=:name");
                        query.setParameter("name", name);
                        UsersEntity user = (UsersEntity) query.getSingleResult();
                        office(user, sc);
                    } catch (NoResultException ex) {
                        System.out.println("No results");
                        break;
                    } catch (NonUniqueResultException ex) {
                        System.out.println("Non unique user found!");
                        break;
                    }
                case 2:
                    flag = true;
                    break;
                default:
                    break;


            }
            if (flag == true) {
                break;
            }
        }
    }

    public static void office(UsersEntity user, Scanner sc) {

        List<AccountsEntity> accs = user.getAccountssById();
        while (true) {
            int i = 0;
            for (AccountsEntity c : accs) {
                i++;
                System.out.println(i + ". " + c.getNumber() + ": " + c.getCash());
            }
            System.out.print("Select card: ");
            cardOperations(accs.get(sc.nextInt()), sc);
        }

    }

    public static void cardOperations(AccountsEntity card, Scanner sc) {
        System.out.println("CARD[" + card.getNumber() + "]: " + card.getCash());
        while (true) {
            System.out.println("1. Replenish on 100 by this course");
            System.out.println("2. Transfer on another card(Doesnt work)");
            switch (sc.nextInt()) {
                case 1:
                    card.replenish(100);
                    break;
                case 2:

            }
        }
    }
}
