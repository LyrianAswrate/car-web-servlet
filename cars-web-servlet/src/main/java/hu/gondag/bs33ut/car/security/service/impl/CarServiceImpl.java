package hu.gondag.bs33ut.car.security.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import hu.gondag.bs33ut.car.dto.CarEditModel;
import hu.gondag.bs33ut.car.dto.CarModel;
import hu.gondag.bs33ut.car.dto.CarModelModel;
import hu.gondag.bs33ut.car.exceptions.CarServiceException;
import hu.gondag.bs33ut.car.security.service.CarModelService;
import hu.gondag.bs33ut.car.security.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);

    @Qualifier("sqliteConncetion")
    @Autowired
    private Connection sqliteConncetion;

    @Autowired
    private CarModelService carModelService;

    @Override
    public CarEditModel getById(Long carId) {
        CarEditModel carEditModel = null;
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(CarService.TABLE_NAME).append(" WHERE ").append(CarService.COLUMN_ID).append(" = ")
                    .append(carId);
            ResultSet resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {
                carEditModel = new CarEditModel();
                carEditModel.setId(resultSet.getLong(CarService.COLUMN_ID));
                carEditModel.setCarModelId(resultSet.getLong(CarService.COLUMN_CARMODEL_ID));
                carEditModel.setOwnerName(resultSet.getString(CarService.COLUMN_OWNER_NAME));
                carEditModel.setLicensePlate(resultSet.getString(CarService.COLUMN_LICENSE_PLATE_NUMBER));
                carEditModel.setVIN(resultSet.getString(CarService.COLUMN_VIN));
                carEditModel.setProductionDate(resultSet.getString(CarService.COLUMN_PRODUCTION_DATE));
                carEditModel.setStartTrafficDate(resultSet.getString(CarService.COLUMN_START_TRAFFIC_DATE));
            }
        } catch (Exception e) {
            CarServiceImpl.LOG.error(e.getMessage(), e);
            carEditModel = null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
        return carEditModel;
    }

    @Override
    public void save(CarEditModel car) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ").append(CarService.TABLE_NAME).append(" (");
            sb.append(CarService.COLUMN_CARMODEL_ID).append(",");
            sb.append(CarService.COLUMN_OWNER_NAME).append(",");
            sb.append(CarService.COLUMN_LICENSE_PLATE_NUMBER).append(",");
            sb.append(CarService.COLUMN_VIN).append(",");
            sb.append(CarService.COLUMN_PRODUCTION_DATE).append(",");
            sb.append(CarService.COLUMN_START_TRAFFIC_DATE);
            sb.append(") VALUES(");
            sb.append(car.getCarModelId()).append(",");
            sb.append("\"").append(car.getOwnerName()).append("\"").append(",");
            sb.append("\"").append(car.getLicensePlate()).append("\"").append(",");
            sb.append("\"").append(car.getVIN()).append("\"").append(",");
            sb.append("\"").append(car.getProductionDate()).append("\"").append(",");
            sb.append("\"").append(car.getStartTrafficDate()).append("\"").append(");");
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            CarServiceImpl.LOG.error(e.getMessage(), e);
            throw new CarServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void update(CarEditModel car) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(CarService.TABLE_NAME).append(" SET ")//
                    .append(CarService.COLUMN_CARMODEL_ID).append(" = ").append(car.getCarModelId()).append(", ")//
                    .append(CarService.COLUMN_OWNER_NAME).append(" = \"").append(car.getOwnerName()).append("\", ")//
                    .append(CarService.COLUMN_LICENSE_PLATE_NUMBER).append(" = \"").append(car.getLicensePlate()).append("\", ")//
                    .append(CarService.COLUMN_VIN).append(" = \"").append(car.getVIN()).append("\", ")//
                    .append(CarService.COLUMN_PRODUCTION_DATE).append(" = \"").append(car.getProductionDate()).append("\", ")//
                    .append(CarService.COLUMN_START_TRAFFIC_DATE).append(" = \"").append(car.getStartTrafficDate()).append("\"")//
                    .append(" WHERE ").append(CarService.COLUMN_ID).append(" = ").append(car.getId());
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            CarServiceImpl.LOG.error(e.getMessage(), e);
            throw new CarServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void deleteById(Long carId) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ").append(CarService.TABLE_NAME).append(" WHERE ").append(CarService.COLUMN_ID).append(" = ")
                    .append(carId);
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            CarServiceImpl.LOG.error(e.getMessage(), e);
            throw new CarServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public List<CarModel> getAll() {
        List<CarModel> carModels = new ArrayList<>();
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(CarService.TABLE_NAME).append(" ORDER BY ").append(CarService.COLUMN_ID).append(" DESC");
            ResultSet resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {
                CarModel carModel = new CarModel();
                carModel.setId(resultSet.getLong(CarService.COLUMN_ID));
                carModel.setCarModelId(resultSet.getLong(CarService.COLUMN_CARMODEL_ID));
                carModel.setOwnerName(resultSet.getString(CarService.COLUMN_OWNER_NAME));
                carModel.setLicensePlate(resultSet.getString(CarService.COLUMN_LICENSE_PLATE_NUMBER));
                carModel.setVIN(resultSet.getString(CarService.COLUMN_VIN));
                carModel.setProductionDate(resultSet.getString(CarService.COLUMN_PRODUCTION_DATE));
                carModel.setStartTrafficDate(resultSet.getString(CarService.COLUMN_START_TRAFFIC_DATE));

                CarModelModel cm = carModelService.getById(carModel.getCarModelId());

                carModel.setCarModel(cm.getModel());
                carModel.setCarModelBrand(cm.getBrand());
                carModel.setCarModelType(cm.getModelType());
                carModel.setExtra(cm.getExtra());

                carModels.add(carModel);
            }
        } catch (Exception e) {
            CarServiceImpl.LOG.error(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
        return carModels;
    }

}
