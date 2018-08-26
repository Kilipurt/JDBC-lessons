package hibernate.lesson4.entities;

import java.util.Date;

public class BookingPeriod {
    private Date dateFrom;
    private Date dateTo;

    public BookingPeriod(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "BookingPeriod{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
