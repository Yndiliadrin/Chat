package hu.alkfejl.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WebappMSG {
    private Messeng messeng;
    private StringProperty sender = new SimpleStringProperty(this, "sender");

    public Messeng getMesseng() {
        return messeng;
    }

    public void setMesseng(Messeng messeng) {
        this.messeng = messeng;
    }

    public String getSender() {
        return sender.get();
    }

    public StringProperty senderProperty() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender.set(sender);
    }
}
