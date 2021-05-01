package hu.alkfejl.dao;

import hu.alkfejl.model.Messeng;
import hu.alkfejl.model.WebappMSG;

import java.util.List;

public interface MessengDAO {
    List<Messeng> findAllMessengBySenderAndReceiver(int sender, int receiver);

    List<WebappMSG> findAllMSGByReceiverAndToWhat(String receiver, int toWhat);

    void  insertMSG(Messeng messeng);
}
