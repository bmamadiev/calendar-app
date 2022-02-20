package ata.unit.one.project.conferencelink;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.String.format;

public class GoogleConferenceLinkHandler implements ConferenceLinkHandler {

    @Override
    public void join(String meetingCode) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URL(format("https://meet.google.com", meetingCode)).toURI());
    }
}
