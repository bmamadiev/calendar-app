package ata.unit.one.project.conferencelink;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface ConferenceLinkHandler {
    void join(String meetingCode) throws IOException, URISyntaxException;
}
