package com.clara.notification.center.service.mapping;

import com.clara.notification.center.domain.Message;
import com.clara.notification.center.service.dto.MessageDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") //, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapping extends GenericEntityMapping<MessageDTO, Message> {

    @Mapping(source = "intId", target = "intId")
        //TODO: deben ser el campo clave de la base de datos ( la llave )
    MessageDTO getDto(Message entity);

    @Mapping(source = "intId", target = "intId")
    Message getEntity(MessageDTO entityDTO);

    default Message fromId(int intId) {
        Message entity = new Message();
        entity.setIntId(intId);
        return entity;
    }
}
