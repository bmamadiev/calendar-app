package ata.unit.one.project.conferencelink;

import java.io.IOException;
import java.net.URISyntaxException;

public class ConferenceLinkContext {
    ConferenceLinkHandler handler = new ConferenceLinkHandler() {
        @Override
        public void join(String meetingCode) throws IOException, URISyntaxException {
            handler.join(meetingCode);
        }
    };
}
