package hu.alkfejl.dao;

import hu.alkfejl.model.Messeng;
import hu.alkfejl.model.WebappMSG;

import java.util.List;

public interface MessengDAO {
    List<WebappMSG> findAllMessengBySenderAndReceiver(int sender, int receiver, int toWhat);

    List<WebappMSG> findAllMSGByReceiverAndToWhat(String receiver, int toWhat);

    void  insertMSG(Messeng messeng);
}
