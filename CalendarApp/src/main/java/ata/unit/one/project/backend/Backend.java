package ata.unit.one.project.backend;

import ata.unit.one.project.backend.models.EventDto;
import ata.unit.one.project.backend.models.PersonDto;
import ata.unit.one.project.backend.models.VideoConferenceLinkDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Backend implements Serializable {
    final String personFileName = "persons.bst";
    final String personEventsFileName = "personevents.bst";
    final String eventsFileName = "events.bst";
    final String conferenceLinksFileName = "conferencelinks.bst";

    HashMap<String, PersonDto> persons;
    HashMap<String, VideoConferenceLinkDto> conferenceLinks;
    HashMap<String, EventDto> events;
    HashMap<String, List<EventDto>> personEvents;

    Storage storage;

    public Backend() {
        storage = new Storage();

        if (events == null) {
            events = new HashMap<>();
        }
        if (personEvents == null) {
            personEvents = new HashMap<>();
        }
        if (conferenceLinks == null) {
            conferenceLinks = new HashMap<>();
        }

        if (persons == null) {
            persons = new HashMap<>() {{
                put("9bd10aae-7070-4bf6-a07e-20cc1344638d", new PersonDto("9bd10aae-7070-4bf6-a07e-20cc1344638d", "Caroline"));
                put("97d99e64-ee9e-4ab7-b9ee-9d66d1282072", new PersonDto("97d99e64-ee9e-4ab7-b9ee-9d66d1282072", "Paulina"));
                put("67793eeb-ff5f-4caf-ad59-19f352e68cb8", new PersonDto("67793eeb-ff5f-4caf-ad59-19f352e68cb8", "Simi"));
                put("88dffed0-7709-4ddf-8099-1ed3df2cf4ab", new PersonDto("88dffed0-7709-4ddf-8099-1ed3df2cf4ab", "Deborah"));
            }};
            initializeEventsForDefaultPersons();
        }
    }

    private void initializeEventsForDefaultPersons() {
        for (String personId : persons.keySet()) {
            personEvents.put(personId, generateRandomEvents());
        }
        storage.store(personFileName, persons);
        storage.store(personEventsFileName, personEvents);
        storage.store(eventsFileName, events);
    }

    public List<PersonDto> getPersons() {
        List<PersonDto> persons = new ArrayList<>();
        for (String personId : this.persons.keySet()) {
            persons.add(this.persons.get(personId));
        }
        return persons;
    }

    public PersonDto addPerson(String personName) {
        String personId = UUID.randomUUID().toString();
        PersonDto personDto = new PersonDto(personId, personName);
        persons.put(personId, personDto);
        storage.store(personFileName, persons);
        return personDto;
    }

    public List<EventDto> getAllPersonMeetingEvents(String personId) {
        List<EventDto> events = null;
        if (personEvents.containsKey(personId)) {
            events = personEvents.get(personId);
        }
        return events;
    }

    public EventDto getEvent(String eventId) {
        return events.get(eventId);
    }

    public List<EventDto> sharedCalendarEvents(String ownerPersonId, String personId) {
        List<EventDto> events = null;
        if (persons.containsKey(ownerPersonId) && persons.containsKey(personId)) {
            List<EventDto> ownerEvents = personEvents.get(ownerPersonId);
            events = personEvents.get(personId);
            if (events == null) {
                events = new ArrayList<>();
            }
            for (EventDto eventDto : ownerEvents) {
                EventDto newEvent = copyEvent(eventDto);
                events.add(newEvent);
                this.events.put(newEvent.getEventId(), newEvent);
            }
            personEvents.put(personId, events);
            storage.store(eventsFileName, this.events);
            storage.store(personEventsFileName, personEvents);
        }
        return events;
    }

    public VideoConferenceLinkDto createVideoLink(String eventId) {
        VideoConferenceLinkDto videoConferenceLinkDto = null;
        if (events.containsKey(eventId) && !conferenceLinks.containsKey(eventId)) {
            var meetingCode = RandomStringUtils.random(9, false, true);
            videoConferenceLinkDto = new VideoConferenceLinkDto(eventId, meetingCode);
            conferenceLinks.put(eventId, videoConferenceLinkDto);
        }
        storage.store(conferenceLinksFileName, conferenceLinks);
        return videoConferenceLinkDto;
    }

    public VideoConferenceLinkDto getEventVideoLink(String eventId) {
        return conferenceLinks.get(eventId);
    }

    public void removeEventVideoLink(String eventId) {
        conferenceLinks.remove(eventId);
        storage.store(conferenceLinksFileName, conferenceLinks);
    }

    private List<EventDto> generateRandomEvents() {
        List<String> eventTitles = Arrays.asList("Doctor's appointment", "Pickup Child", "Lunch");
        List<EventDto> events = new ArrayList<>();
        Random rand = new Random();
        int numberOfEvents = rand.nextInt(9) + 1;
        for (int i = 0; i < numberOfEvents; i++) {
            int randIndex = new Random().nextInt(eventTitles.size());
            String eventTitle = eventTitles.get(randIndex);
            String eventId = UUID.randomUUID().toString();
            EventDto event = new EventDto(eventId, eventTitle, getVideoConferenceProvider(), Instant.now().plus(i, ChronoUnit.DAYS), Instant.now().plus(i, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS));
            events.add(event);
            this.events.put(eventId, event);
        }
        return events;
    }

    private EventDto copyEvent(EventDto event) {
        return new EventDto(UUID.randomUUID().toString(), event.getEventTitle(), getVideoConferenceProvider(), event.getStartTime(), event.getEndTime());
    }

    private String getVideoConferenceProvider() {
        List<String> videoConferenceProvider = Arrays.asList("Zoom", "Google");
        int randIndex = new Random().nextInt(videoConferenceProvider.size());
        return videoConferenceProvider.get(randIndex);
    }
}