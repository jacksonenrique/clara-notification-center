package com.clara.notification.center.web.rest;

import com.clara.notification.center.service.MessageService;
import com.clara.notification.center.service.dto.MessageDTO;
import com.clara.notification.center.web.rest.Assembler.MessageResourceAssembler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
@Api(value="Messages API", description="Notification Inbox")
public class MessageResource {
    private final Logger log = LoggerFactory.getLogger(MessageResource.class);
    private final MessageService messageService;
    private final MessageResourceAssembler messageResourceAssembler;

    public MessageResource(MessageService messageService,
                           MessageResourceAssembler messageResourceAssembler) {
        this.messageService = messageService;
        this.messageResourceAssembler = messageResourceAssembler;
    }

    @ApiOperation(value = "try list all notifications", response = Iterable.class)
        @ApiResponses(value = {
            @ApiResponse(code = 201, message = "process completed successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 403, message = "Accessing the resource you are trying to reach is prohibited"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach cannot be found")
        }
    )
    @GetMapping("/notification")
    public Resources<Resource<MessageDTO>> GetEntities() {

        List<Resource<MessageDTO>> entities = null;

        try {
            entities = this.messageService.GetEntities().parallelStream()
                    .map(messageResourceAssembler::toResource)
                    .collect(Collectors.toList());
            Resources<Resource<MessageDTO>> returnResource = new Resources<Resource<MessageDTO>>(entities);
            returnResource.add(linkTo(methodOn(MessageResource.class).GetEntities()).withSelfRel());
            return returnResource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "return a notification requested", response = Iterable.class)
        @ApiResponses(value = {
            @ApiResponse(code = 201, message = "process completed successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 403, message = "Accessing the resource you are trying to reach is prohibited"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach cannot be found")
        }
    )
    @GetMapping("/notification/{id}")
    public ResponseEntity<Resource<MessageDTO>> FindEntity(@PathVariable int id) {

        log.debug(String.format("clara-notification-center FindEntity() invoke:{} by {} ",
                messageService.getClass().getName(), id));

        try {
            return Optional.of(this.messageService.FindEntity(id))
                    .map(u -> new ResponseEntity<>(messageResourceAssembler.toResource(u), HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            log.error("An error occurred in the FindEntity REST call", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Save notification data", response = Iterable.class)
        @ApiResponses(value = {
            @ApiResponse(code = 201, message = "process completed successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 403, message = "Accessing the resource you are trying to reach is prohibited"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach cannot be found")
        }
    )
    @PostMapping("/notification")
    public ResponseEntity<?> SaveEntity(@RequestBody MessageDTO entityDTO) {

        Resource<MessageDTO> resource = null;

        try {
            resource = messageResourceAssembler.toResource(messageService.SaveUpdate(entityDTO));

            return ResponseEntity
                    .created(new URI(resource.getId().expand().getHref()))
                    .body(resource);
        } catch (Exception e) {
            log.warn("An error occurred in the SaveEntity REST call", e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Update requested notification", response = Iterable.class)
        @ApiResponses(value = {
            @ApiResponse(code = 201, message = "process completed successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 403, message = "Accessing the resource you are trying to reach is prohibited"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach cannot be found")
        }
    )
    @PutMapping("/notification/{id}")
    public ResponseEntity<?> UpdateEntity(@RequestBody MessageDTO entityDTO, @PathVariable Long id) {
        return SaveEntity(entityDTO);
    }

    @ApiOperation(value = "Delete the requested notification", response = Iterable.class)
        @ApiResponses(value = {
            @ApiResponse(code = 201, message = "process completed successfully"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 403, message = "Accessing the resource you are trying to reach is prohibited"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach cannot be found")
        }
    )
    @DeleteMapping("/notification/{id}")
    ResponseEntity<?> RemoveEntity(@PathVariable int id) {
        log.debug("REST request to Delete entity: {}", id);
        try {
            messageService.RemoveEntity(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }
}
