package br.com.edu.ufcg.osindico.Utils;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;

public interface ItemClickListener {
    void onClick(DwellerResponse dwellerResponse);

    void onClick(MessageResponse messageResponse);
}
