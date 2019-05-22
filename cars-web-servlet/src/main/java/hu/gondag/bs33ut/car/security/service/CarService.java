package hu.gondag.bs33ut.car.security.service;

import java.util.List;

import hu.gondag.bs33ut.car.dto.CarEditModel;
import hu.gondag.bs33ut.car.dto.CarModel;

public interface CarService {

    public static final String TABLE_NAME = "CARS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CARMODEL_ID = "CARMODEL_ID";
    public static final String COLUMN_OWNER_NAME = "OWNER_NAME";
    public static final String COLUMN_LICENSE_PLATE_NUMBER = "LICENSE_PLATE_NUMBER";
    public static final String COLUMN_VIN = "VIN";
    public static final String COLUMN_PRODUCTION_DATE = "PRODUCTION_DATE";
    public static final String COLUMN_START_TRAFFIC_DATE = "START_TRAFFIC_DATE";

    public CarEditModel getById(Long carId);

    public void save(CarEditModel car);

    public void update(CarEditModel car);

    public void deleteById(Long carId);

    public List<CarModel> getAll();

}
