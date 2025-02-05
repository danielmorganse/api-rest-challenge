package cl.tenpo.rest.api.challenge.utils;

import cl.tenpo.rest.api.challenge.dtos.HistoryRecord;
import cl.tenpo.rest.api.challenge.entities.ApiCallRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

public class HistoryRecordConverter {

    @Autowired
    private ModelMapper modelMapper;

    private HistoryRecord convertToDto(ApiCallRecord entity) {
        return modelMapper.map(entity, HistoryRecord.class);
    }
}
