package com.clara.notification.center.web.rest.Assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.clara.notification.center.service.dto.MessageDTO;
import com.clara.notification.center.web.rest.MessageResource;

@Component
public class MessageResourceAssembler implements ResourceAssembler<MessageDTO, Resource<MessageDTO>> {
    @Override
    public Resource<MessageDTO> toResource(MessageDTO entityDTO) {
        return new Resource<>(entityDTO,
                linkTo(methodOn(MessageResource.class).FindEntity(entityDTO.getIntId())).withSelfRel(),
                linkTo(methodOn(MessageResource.class).GetEntities()).withRel("Message"));
    }
}
