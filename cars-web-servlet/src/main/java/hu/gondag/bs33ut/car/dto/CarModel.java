package hu.gondag.bs33ut.car.dto;

public class CarModel {

    private Long id;
    private Long carModelId;
    private String carModelBrand;
    private String carModel;
    private String carModelType;
    private String extra;
    private String ownerName;
    private String licensePlate;
    private String VIN;
    private String productionDate;
    private String startTrafficDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarModelBrand() {
        return carModelBrand;
    }

    public void setCarModelBrand(String carModelBrand) {
        this.carModelBrand = carModelBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModelType() {
        return carModelType;
    }

    public void setCarModelType(String carModelType) {
        this.carModelType = carModelType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getStartTrafficDate() {
        return startTrafficDate;
    }

    public void setStartTrafficDate(String startTrafficDate) {
        this.startTrafficDate = startTrafficDate;
    }

}
