package com.artemsirosh.tij.concurrency.conveyor;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created at 22-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Vehicle {

    private final String identityNumber;
    private String engineIdentityNumber;
    private String transmissionIdentityNumber;
    private String driveTrainIdentityNumber;
    private String onBoardElectronicsNumber;
    private String interiorQualityCheck;
    private String exteriorSuiteNumber;

    Vehicle(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    String getIdentityNumber() {
        return identityNumber;
    }

    String getEngineIdentityNumber() {
        return engineIdentityNumber;
    }

    void setEngineIdentityNumber(String engineIdentityNumber) {
        this.engineIdentityNumber = engineIdentityNumber;
    }

    String getTransmissionIdentityNumber() {
        return transmissionIdentityNumber;
    }

    void setTransmissionIdentityNumber(String transmissionIdentityNumber) {
        this.transmissionIdentityNumber = transmissionIdentityNumber;
    }

    String getDriveTrainIdentityNumber() {
        return driveTrainIdentityNumber;
    }

    void setDriveTrainIdentityNumber(String driveTrainIdentityNumber) {
        this.driveTrainIdentityNumber = driveTrainIdentityNumber;
    }

    String getOnBoardElectronicsNumber() {
        return onBoardElectronicsNumber;
    }

    void setOnBoardElectronicsNumber(String onBoardElectronicsNumber) {
        this.onBoardElectronicsNumber = onBoardElectronicsNumber;
    }

    String getInteriorQualityCheck() {
        return interiorQualityCheck;
    }

    void setInteriorQualityCheck(String interiorQualityCheck) {
        this.interiorQualityCheck = interiorQualityCheck;
    }

    String getExteriorSuiteNumber() {
        return exteriorSuiteNumber;
    }

    void setExteriorSuiteNumber(String exteriorSuiteNumber) {
        this.exteriorSuiteNumber = exteriorSuiteNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return identityNumber.equals(vehicle.identityNumber) &&
                Objects.equals(engineIdentityNumber, vehicle.engineIdentityNumber) &&
                Objects.equals(transmissionIdentityNumber, vehicle.transmissionIdentityNumber) &&
                Objects.equals(driveTrainIdentityNumber, vehicle.driveTrainIdentityNumber) &&
                Objects.equals(onBoardElectronicsNumber, vehicle.onBoardElectronicsNumber) &&
                Objects.equals(interiorQualityCheck, vehicle.interiorQualityCheck) &&
                Objects.equals(exteriorSuiteNumber, vehicle.exteriorSuiteNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityNumber, engineIdentityNumber, transmissionIdentityNumber, driveTrainIdentityNumber, onBoardElectronicsNumber, interiorQualityCheck, exteriorSuiteNumber);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Vehicle.class.getSimpleName() + "[", "]")
                .add("VIN = " + identityNumber)
                .add("engine = " + engineIdentityNumber)
                .add("transmission = " + transmissionIdentityNumber)
                .add("drive train = " + driveTrainIdentityNumber)
                .add("interior quality check = " + interiorQualityCheck)
                .add("on-board electronics = " + onBoardElectronicsNumber)
                .add("an exterior suite = " + exteriorSuiteNumber)
                .toString();
    }
}
