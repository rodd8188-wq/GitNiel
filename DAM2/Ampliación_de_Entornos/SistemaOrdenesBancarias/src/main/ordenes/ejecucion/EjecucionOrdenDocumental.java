package main.ordenes.ejecucion;

import main.ordenes.documentos.Talon;
import main.ordenes.transferencia.OrdenDTO;
import main.ordenes.transferencia.SolicitudDTO;

import java.util.List;

public class EjecucionOrdenDocumental {

    public SolicitudDTO tramitar(OrdenDTO ordenDTO) {
        SolicitudDTO solicitudDTO;
        
        solicitudDTO = new SolicitudDTO(List.of(new Talon()));

        return solicitudDTO;
    }

}
