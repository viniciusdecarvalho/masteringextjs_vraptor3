package mvcs.test;

import mvcs.model.dto.DtoActor;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


public class JsonTest {
	
	@Test
	public void testJson() {
		
		String data = "{data:[{'firstName':'PENELOPE1','lastName':'GUINESS',id:1},{'firstName':'NICK1','lastName':'WAHLBERG',id:2}]}";

		XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.processAnnotations(DtoActor.class);
        Object object = xStream.fromXML(data);
        System.out.println(object);
		
	}
	
}
