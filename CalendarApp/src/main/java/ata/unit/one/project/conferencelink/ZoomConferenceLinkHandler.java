package ata.unit.one.project.conferencelink;

import ata.unit.one.project.backend.models.EventDto;
import ata.unit.one.project.backend.models.VideoConferenceLinkDto;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.String.format;

public class ZoomConferenceLinkHandler implements ConferenceLinkHandler {

    @Override
    public void join(String meetingCode) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URL(format("https://zoom.us/j/%s", meetingCode)).toURI());
    }
}
