package hibernate.lesson4.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    private Long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    @Id
    @SequenceGenerator(name = "OR_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ORDERED", nullable = false)
    public User getUserOrdered() {
        return userOrdered;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROOM", nullable = false)
    public Room getRoom() {
        return room;
    }

    @Column(name = "DATE_FROM")
    public Date getDateFrom() {
        return dateFrom;
    }

    @Column(name = "DATE_TO")
    public Date getDateTo() {
        return dateTo;
    }

    @Column(name = "MONEY_PAID")
    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserOrdered(User userOrdered) {
        this.userOrdered = userOrdered;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }
}
