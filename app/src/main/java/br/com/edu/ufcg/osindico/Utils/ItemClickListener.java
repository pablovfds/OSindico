package br.com.edu.ufcg.osindico.Utils;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;

public interface ItemClickListener {
    void onClick(DwellerResponse dwellerResponse);

    void onClick(MessageResponse messageResponse);

    void onClick(ServiceRequestResponse serviceRequestResponse);
}
