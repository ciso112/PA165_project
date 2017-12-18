package com.muni.fi.pa165project.rest.controllers;

import com.muni.fi.pa165project.dto.*;
import com.muni.fi.pa165project.facade.TrackingFacade;
import com.muni.fi.pa165project.rest.ApiUris;
import com.muni.fi.pa165project.rest.exceptions.AlreadyExistsException;
import com.muni.fi.pa165project.rest.exceptions.InternalException;
import com.muni.fi.pa165project.rest.exceptions.ResourceNotFoundException;
import com.muni.fi.pa165project.rest.exceptions.UnprocessableEntityException;
import com.muni.fi.pa165project.rest.security.ApplyAuthorizeFilter;
import com.muni.fi.pa165project.rest.security.SecurityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * REST Controller for Records
 *
 * @author Peter Krasnan.
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_RECORDS)
public class RecordsController {

    final static Logger logger = LoggerFactory.getLogger(RecordsController.class);

    @Inject
    private TrackingFacade trackingFacade;

    /**
     * Create a new Record
     *
     * @param recordDTO RecordCreateDTO with required fields for creation
     * @throws AlreadyExistsException       if resource already exists
     * @throws UnprocessableEntityException if resource contains wrong attributes
     * @throws InternalException            in case of any other error
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Long createRecord(@RequestAttribute("userId") long userId, @RequestBody RecordCreateDTO recordDTO) {
        logger.debug("rest createRecord for user {}", userId);
        recordDTO.setUserId(userId);
        try {
            return trackingFacade.createRecord(recordDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException();
        } catch (ConstraintViolationException ex) {
            throw new UnprocessableEntityException();
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    /**
     * Get list of Records.
     *
     * @return List of RecordDTO
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<RecordDTO> getAllRecords(@RequestAttribute("userId") long userId) {
        logger.debug("rest getAllRecords() for user {}", userId);
        return trackingFacade.getAllRecords(userId);
    }


    /**
     * Delete Record by identifier.
     * Identifier is taken from the URL path.
     *
     * @throws ResourceNotFoundException if resource is not found
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteRecord(@PathVariable("id") long id) {
        logger.debug("rest deleteRecord({})", id);
        try {
            trackingFacade.removeRecord(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Edit the Record referred by identifier.
     * Identifier is taken from the URL path.
     * New data are sent in HTTP BODY by PUT method.
     * Name must match the Record which is referred by ID in URL path.
     * <p>
     *
     * @param recordUpdateDTO RecordUpdateDTO with required fields for edit
     * @throws UnprocessableEntityException if resource contains wrong attributes
     * @throws InternalException            in case of any other error
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void editRecord(@PathVariable("id") long id, @RequestAttribute("userId") long userId, @RequestBody RecordUpdateDTO recordUpdateDTO) {
        logger.debug("rest editRecord()");
        try {
            recordUpdateDTO.setUserId(userId);
            recordUpdateDTO.setId(id);
            trackingFacade.editRecord(recordUpdateDTO);
        } catch (ConstraintViolationException ex) {
            throw new UnprocessableEntityException();
        } catch (Exception ex) {
            throw new InternalException();
        }

    }

    /**
     * Get a Record detail by identifier.
     * Identifier is taken from the URL path.
     *
     * @param id identifier for a record
     * @return Record detail Data Transfer Object
     * @throws ResourceNotFoundException if resource is not found
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RecordDetailDTO getRecord(@PathVariable("id") long id) {
        logger.debug("rest getRecord({})", id);
        RecordDetailDTO record = trackingFacade.getRecord(id);
        if (record != null) {
            return record;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/getForUpdate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RecordGetUpdateDTO getRecordGetUpdateDTO(@PathVariable("id") long id) {
        logger.debug("rest getRecordGetUpdateDTO({})", id);
        RecordGetUpdateDTO record = trackingFacade.getRecordGetUpdateDTO(id);
        if (record != null) {
            return record;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/getForUpdate/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RecordGetUpdateDTO editRecordGetUpdateDTO(@PathVariable("id") long id) {
        logger.debug("rest getRecordGetUpdateDTO({})", id);
        RecordGetUpdateDTO record = trackingFacade.getRecordGetUpdateDTO(id);
        if (record != null) {
            return record;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Get the user's weekly goal progress by user identifier.
     * Identifier is taken from login info.
     *
     * @param userId identifier for a user
     * @return goal progress
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.MEMBER)
    @RequestMapping(value = "/progress", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final int getGoalProgress(@RequestAttribute("userId") long userId) {
        logger.debug("rest getRecord({})", userId);
        try {
            return trackingFacade.getWeekProgressOfBurnedCalories(userId);
        } catch (Exception ex) {
            return 0;
        }
    }
}
