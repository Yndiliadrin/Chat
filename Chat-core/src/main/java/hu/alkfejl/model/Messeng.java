package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.sql.Blob;

public class Messeng {
    private IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private IntegerProperty sender = new SimpleIntegerProperty(this, "sender");
    private IntegerProperty receiver = new SimpleIntegerProperty(this, "receiver");
    private StringProperty type = new SimpleStringProperty(this, "type");
    private StringProperty messeng = new SimpleStringProperty(this, "messeng");
    private byte[] img;
    private IntegerProperty to_what = new SimpleIntegerProperty(this, "to_what");

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public int getSender() {
        return sender.get();
    }

    public IntegerProperty senderProperty() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender.set(sender);
    }

    public int getReceiver() {
        return receiver.get();
    }

    public IntegerProperty receiverProperty() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver.set(receiver);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getMesseng() {
        return messeng.get();
    }

    public StringProperty messengProperty() {
        return messeng;
    }

    public void setMesseng(String messeng) {
        this.messeng.set(messeng);
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getTo_what() {
        return to_what.get();
    }

    public IntegerProperty to_whatProperty() {
        return to_what;
    }

    public void setTo_what(int to_what) {
        this.to_what.set(to_what);
    }
}
