package br.com.edu.ufcg.osindico.Utils;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ResidentResponse;

public interface ItemClickListener {
    void onClick(ResidentResponse residentResponse);

    void onClick(MessageResponse messageResponse);
}
