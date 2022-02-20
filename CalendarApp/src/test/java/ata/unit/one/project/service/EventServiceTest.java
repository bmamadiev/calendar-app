package ata.unit.one.project.service;

import ata.unit.one.project.backend.Backend;
import ata.unit.one.project.models.Event;
import ata.unit.one.project.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {


    @Test
    void return_events_for_person() {
        // GIVEN - create event service
        Backend backend = new Backend();
        EventService eventService = new EventService(backend);
        PersonService personService = new PersonService(backend);

        // WHEN - call get meeting events
        Person person = personService.getPersons().get(0);
        List<Event> events = eventService.getMeetingEvents(person.getPersonId());

        // THEN
        assertTrue(events.size() > 0, "Expected events to be returned for person");
    }

    @Test
    void return_zero_events_for_person_that_does_not_exist() {
        // GIVEN - create event service
        Backend backend = new Backend();
        EventService eventService = new EventService(backend);

        // WHEN - construct TimestampedData
        String person = "Someone";

        // THEN
        assertThrows(IllegalArgumentException.class, () -> eventService.getMeetingEvents(person), "Expected to throw IllegalArgumentException");
    }

    //TODO: Finish the implementation of this test case
    @Test
    void share_calendar_returns_distinct_list() {
        // GIVEN - create event service
        Backend backend = new Backend();
        EventService eventService = new EventService(backend);
        String ownerPersonId = "97d99e64-ee9e-4ab7-b9ee-9d66d1282072";
        String newPersonId = "9bd10aae-7070-4bf6-a07e-20cc1344638d";
        List<Event> ownerPersonEvents = eventService.getMeetingEvents(ownerPersonId);
        List<Event> sharedList = new ArrayList<>();

        // WHEN - share events with newly created person
        sharedList = eventService.shareCalendarEvents(ownerPersonId, newPersonId);

        //THEN - new person has the same event IDs
        Assertions.assertTrue(sharedList.containsAll(ownerPersonEvents));
    }
}