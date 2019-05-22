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

import hu.gondag.bs33ut.car.dto.CarModelModel;
import hu.gondag.bs33ut.car.exceptions.CarModelServiceException;
import hu.gondag.bs33ut.car.security.service.CarModelService;

@Service
public class CarModelServiceImpl implements CarModelService {

    private static final Logger LOG = LoggerFactory.getLogger(CarModelService.class);

    @Qualifier("sqliteConncetion")
    @Autowired
    private Connection sqliteConncetion;

    @Override
    public CarModelModel getById(Long carModelId) {
        CarModelModel carModelModel = null;
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(CarModelService.TABLE_NAME).append(" WHERE ").append(CarModelService.COLUMN_ID).append(" = ")
                    .append(carModelId);
            ResultSet resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {
                carModelModel = new CarModelModel();
                carModelModel.setId(resultSet.getLong(CarModelService.COLUMN_ID));
                carModelModel.setBrand(resultSet.getString(CarModelService.COLUMN_BRAND));
                carModelModel.setModel(resultSet.getString(CarModelService.COLUMN_MODEL));
                carModelModel.setModelType(resultSet.getString(CarModelService.COLUMN_MODEL_TYPE));
                carModelModel.setExtra(resultSet.getString(CarModelService.COLUMN_EXTRA));
            }
        } catch (Exception e) {
            CarModelServiceImpl.LOG.error(e.getMessage(), e);
            carModelModel = null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarModelServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
        return carModelModel;
    }

    @Override
    public void save(CarModelModel carModel) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ").append(CarModelService.TABLE_NAME).append(" (");
            sb.append(CarModelService.COLUMN_BRAND).append(",");
            sb.append(CarModelService.COLUMN_MODEL).append(",");
            sb.append(CarModelService.COLUMN_MODEL_TYPE).append(",");
            sb.append(CarModelService.COLUMN_EXTRA);
            sb.append(") VALUES(");
            sb.append("\"").append(carModel.getBrand()).append("\"").append(",");
            sb.append("\"").append(carModel.getModel()).append("\"").append(",");
            sb.append("\"").append(carModel.getModelType()).append("\"").append(",");
            sb.append("\"").append(carModel.getExtra()).append("\"").append(");");
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            CarModelServiceImpl.LOG.error(e.getMessage(), e);
            throw new CarModelServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarModelServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void update(CarModelModel carModel) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(CarModelService.TABLE_NAME).append(" SET ")//
                    .append(CarModelService.COLUMN_MODEL).append(" = \"").append(carModel.getBrand()).append("\", ")//
                    .append(CarModelService.COLUMN_MODEL_TYPE).append(" = \"").append(carModel.getModel()).append("\", ")//
                    .append(CarModelService.COLUMN_EXTRA).append(" = \"").append(carModel.getModelType()).append("\", ")//
                    .append(CarModelService.COLUMN_BRAND).append(" = \"").append(carModel.getExtra()).append("\"")//
                    .append(" WHERE ").append(CarModelService.COLUMN_ID).append(" = ").append(carModel.getId());
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            CarModelServiceImpl.LOG.error(e.getMessage(), e);
            throw new CarModelServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarModelServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void deleteById(Long carModelId) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ").append(CarModelService.TABLE_NAME).append(" WHERE ").append(CarModelService.COLUMN_ID).append(" = ")
                    .append(carModelId);
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            CarModelServiceImpl.LOG.error(e.getMessage(), e);
            throw new CarModelServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarModelServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public List<CarModelModel> getAll() {
        List<CarModelModel> carModelModels = new ArrayList<>();
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(CarModelService.TABLE_NAME).append(" ORDER BY ").append(CarModelService.COLUMN_ID)
                    .append(" DESC");
            ResultSet resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {
                CarModelModel carModelModel = new CarModelModel();
                carModelModel.setId(resultSet.getLong(CarModelService.COLUMN_ID));
                carModelModel.setBrand(resultSet.getString(CarModelService.COLUMN_BRAND));
                carModelModel.setModel(resultSet.getString(CarModelService.COLUMN_MODEL));
                carModelModel.setModelType(resultSet.getString(CarModelService.COLUMN_MODEL_TYPE));
                carModelModel.setExtra(resultSet.getString(CarModelService.COLUMN_EXTRA));

                carModelModels.add(carModelModel);
            }
        } catch (Exception e) {
            CarModelServiceImpl.LOG.error(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    CarModelServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
        return carModelModels;
    }

}
