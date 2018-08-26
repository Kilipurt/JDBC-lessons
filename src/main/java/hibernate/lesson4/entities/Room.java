package hibernate.lesson4.entities;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {
    private Long id;
    private Integer numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Hotel hotel;

    @Id
    @SequenceGenerator(name = "ROOM_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "NUMBER_OF_GUESTS")
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @Column(name = "BREAKFAST_INCLUDED")
    public int getBreakfastIncluded() {
        return breakfastIncluded ? 1 : 0;
    }

    @Transient
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Column(name = "PETS_ALLOWED")
    public int getPetsAllowed() {
        return petsAllowed ? 1 : 0;
    }

    @Transient
    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOTEL", nullable = false)
    public Hotel getHotel() {
        return hotel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(int breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded == 1;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(int petsAllowed) {
        this.petsAllowed = petsAllowed == 1;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", hotel=" + hotel.getId() +
                '}';
    }
}
