package hu.gondag.bs33ut.car.security.service;

import java.util.List;

import hu.gondag.bs33ut.car.dto.CarModelModel;

public interface CarModelService {

    public static final String TABLE_NAME = "CARMODELS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BRAND = "BRAND";
    public static final String COLUMN_MODEL = "MODEL";
    public static final String COLUMN_MODEL_TYPE = "MODEL_TYPE";
    public static final String COLUMN_EXTRA = "EXTRA";

    public CarModelModel getById(Long carModelId);

    public void save(CarModelModel carModel);

    public void update(CarModelModel carModel);

    public void deleteById(Long carModelId);

    public List<CarModelModel> getAll();

}
