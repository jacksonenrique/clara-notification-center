package com.clara.notification.center.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.clara.notification.center.domain.Message;
import com.clara.notification.center.repository.MessageRepository;
import com.clara.notification.center.service.MessageService;
import com.clara.notification.center.service.dto.MessageDTO;
import com.clara.notification.center.service.mapping.MessageMapping;
import com.clara.notification.center.service.implementation.Exceptions.NotFoundEntityException;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    
    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final MessageRepository messageRepository;
    private final MessageMapping messageMapping = Mappers.getMapper(MessageMapping.class);

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageDTO SaveUpdate(MessageDTO messageDTO) throws Exception {
        log.debug("Request to save entity : {}", messageDTO);

        //TODO: add service class specific validation
        Message message = messageMapping.getEntity(messageDTO);
        message = messageRepository.save(message);

        MessageDTO currentMessageDTO = messageMapping.getDto(message);
        return currentMessageDTO;
    }

    @Override
    public List<MessageDTO> GetEntities() throws Exception {
        log.debug("Request to list all Entities");
        return messageMapping.getDto(messageRepository.findAll());
    }

    @Override
    public MessageDTO FindEntity(int id) throws Exception {
        log.debug("Request to find especific Entity : {}", id);
        Message currentEntity = messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id));
        return messageMapping.getDto(currentEntity);
    }

    @Override
    public void RemoveEntity(int id) throws Exception {
        log.debug("Request to delete entity : {}", id);
        messageRepository.deleteById(id);
    }
}
