package com.doit.wheels.dao.entities;

import javax.persistence.Lob;

public class PicturesDto {
    @Lob
    private byte[] wheelsRimPicture1;

    @Lob
    private byte[] wheelsRimPicture2;

    @Lob
    private byte[] wheelsRimPicture3;

    @Lob
    private byte[] wheelsRimPicture4;

    public byte[] getWheelsRimPicture1() {
        return wheelsRimPicture1;
    }

    public void setWheelsRimPicture1(byte[] wheelsRimPicture1) {
        this.wheelsRimPicture1 = wheelsRimPicture1;
    }

    public byte[] getWheelsRimPicture2() {
        return wheelsRimPicture2;
    }

    public void setWheelsRimPicture2(byte[] wheelsRimPicture2) {
        this.wheelsRimPicture2 = wheelsRimPicture2;
    }

    public byte[] getWheelsRimPicture3() {
        return wheelsRimPicture3;
    }

    public void setWheelsRimPicture3(byte[] wheelsRimPicture3) {
        this.wheelsRimPicture3 = wheelsRimPicture3;
    }

    public byte[] getWheelsRimPicture4() {
        return wheelsRimPicture4;
    }

    public void setWheelsRimPicture4(byte[] wheelsRimPicture4) {
        this.wheelsRimPicture4 = wheelsRimPicture4;
    }
}
