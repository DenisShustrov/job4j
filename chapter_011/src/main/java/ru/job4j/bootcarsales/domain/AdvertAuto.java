package ru.job4j.bootcarsales.domain;



import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Class AdvertAuto.
 *
 * @author dshustrov
 * @version 1
 * @since 30.04.2019
 */
@Entity
@Table(name = "advertauto")
public class AdvertAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mark_a")
    private String mark;

    @Column(name = "model_a")
    private String model;

    @Column(name = "years_a")
    private String years;

    @Column(name = "body_type_a")
    private String bodyType;

    @Column(name = "engine_type_a")
    private String engineType;

    @Column(name = "drive_a")
    private String drive;

    @Column(name = "transmission_a")
    private String transmission;

    @Column(name = "mileage_a")
    private double mileage;

    @Column(name = "condition_a")
    private String condition;

    @Column(name = "description_a")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(name = "photo_path_a")
    private String photoPath;

    @Column(name = "price_a")
    private double price;

    @Column(name = "name_seller_a")
    private String nameSeller;

    @Column(name = "phone_number_a")
    private String phoneNumber;

    @Column(name = "sale_status_a")
    private boolean saleStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdate_a")
    private Date createDate;

    public AdvertAuto(String mark,
                      String model,
                      String years,
                      String bodyType,
                      String engineType,
                      String drive,
                      String transmission,
                      double mileage,
                      String condition,
                      String description,
                      double price,
                      String nameSeller,
                      String phoneNumber) {
        this.mark = mark;
        this.model = model;
        this.years = years;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.drive = drive;
        this.transmission = transmission;
        this.mileage = mileage;
        this.condition = condition;
        this.description = description;
        this.price = price;
        this.nameSeller = nameSeller;
        this.phoneNumber = phoneNumber;
        this.createDate = new Date();
    }

    public AdvertAuto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(boolean saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getYears() {
        return years;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setYears(String years) {
        this.years = years;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdvertAuto that = (AdvertAuto) o;
        return id == that.id
                &&
                Double.compare(that.mileage, mileage) == 0
                &&
                Double.compare(that.price, price) == 0
                &&
                saleStatus == that.saleStatus
                &&
                Objects.equals(mark, that.mark)
                &&
                Objects.equals(model, that.model)
                &&
                Objects.equals(years, that.years)
                &&
                Objects.equals(bodyType, that.bodyType)
                &&
                Objects.equals(engineType, that.engineType)
                &&
                Objects.equals(drive, that.drive)
                &&
                Objects.equals(transmission, that.transmission)
                &&
                Objects.equals(condition, that.condition)
                &&
                Objects.equals(description, that.description)
                &&
                Objects.equals(seller, that.seller)
                &&
                Objects.equals(photoPath, that.photoPath)
                &&
                Objects.equals(nameSeller, that.nameSeller)
                &&
                Objects.equals(phoneNumber, that.phoneNumber)
                &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                mark,
                model,
                years,
                bodyType,
                engineType,
                drive,
                transmission,
                mileage,
                condition,
                description,
                seller,
                photoPath,
                price,
                nameSeller,
                phoneNumber,
                saleStatus,
                createDate);
    }

    @Override
    public String toString() {
        return "AdvertAuto{"
                +
                "id=" + id
                +
                ", mark='" + mark + '\''
                +
                ", model='" + model + '\''
                +
                ", years='" + years + '\''
                +
                ", bodyType='" + bodyType + '\''
                +
                ", engineType='" + engineType + '\''
                +
                ", drive='" + drive + '\''
                +
                ", transmission='" + transmission + '\''
                +
                ", mileage=" + mileage
                +
                ", condition='" + condition + '\''
                +
                ", description='" + description + '\''
                +
                ", photoPath='" + photoPath + '\''
                +
                ", price=" + price
                +
                ", nameSeller='" + nameSeller + '\''
                +
                ", phoneNumber='" + phoneNumber + '\''
                +
                ", saleStatus=" + saleStatus
                +
                ", createDate=" + createDate
                +
                '}';
    }
}
