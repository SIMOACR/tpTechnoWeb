package com.technoWeb.Tp.service.event;

import com.technoWeb.Tp.exception.EventErrorMessages;
import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.exception.UnauthorizedException;
import com.technoWeb.Tp.model.Event;
import com.technoWeb.Tp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    public List<Event> findAll() {
        List<EventEntity> facilityEntities = eventRepository.findAll();
        if (!facilityEntities.isEmpty())
            return facilityEntities.stream()
                    .map(eventMapper::toModel)
                    .collect(Collectors.toList());
        else
            throw new NoContentException(EventErrorMessages.NO_EVENT.name());
    }

    public Event findById(long id) {
        return eventRepository.findById(id).map(eventMapper::toModel)
                .orElseThrow(() -> new NotFoundException(EventErrorMessages.EVENT_NOT_FOUND.name()));
    }

    public Event create(Event event) {
        EventEntity eventEntity = eventMapper.fromModel(event);
        long id = event.getId();
        if(id == 0) {
            eventEntity.setId(id);
            return eventMapper.toModel(eventRepository.save(eventEntity));
        } else {
            throw new UnauthorizedException(EventErrorMessages.EVENT_ALREADY_EXIST.name());
        }
    }

    public Event update(Event event) {
        EventEntity eventEntity = eventMapper.fromModel(event);
        long id = event.getId();
        if(id != 0) {
            eventEntity.setId(id);
            return eventMapper.toModel(eventRepository.save(eventEntity));
        } else {
            throw new UnauthorizedException(EventErrorMessages.NEW_EVENT.name());
        }
    }

    public Event delete(long id) {
        Optional<EventEntity> eventEntityOptional = eventRepository.findById(id);
        if (eventEntityOptional.isPresent()) {
            EventEntity facilityEntity = eventEntityOptional.get();
            eventRepository.deleteById(id);
            return eventMapper.toModel(facilityEntity);
        } else
            throw new NotFoundException(EventErrorMessages.EVENT_NOT_FOUND.name());
    }


}
