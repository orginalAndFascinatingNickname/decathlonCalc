package com.decathlon.calc.Writer;

import com.decathlon.calc.Domain.DecathlonEventResult;
import com.decathlon.calc.Domain.DecathlonEventType;
import com.decathlon.calc.Domain.DecathlonResultEntry;
import com.decathlon.calc.Reader.IDecathlonResultReader;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlDecathlonResultWriter implements IDecathlonResultWriter {

    private String outputFilename;

    public XmlDecathlonResultWriter(String outputFile) {
        outputFilename = outputFile;
    }

    @Override
    public void writeAllResultEntries(List<DecathlonResultEntry> resultEntries) {

//        File outputFile = new File(outputFilename);
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("results");
            document.appendChild(root);

            for(DecathlonResultEntry entry : resultEntries) {
                //Create an XML tag for each event + total score + place
                Element athlete = document.createElement("athlete");
                athlete.setAttribute("name", entry.getAthleteName());

                Element place = document.createElement("place");
                place.appendChild(document.createTextNode(entry.getPlace()));

                Element totalScore = document.createElement("totalScore");
                totalScore.appendChild(
                        document.createTextNode(entry.getTotalScore().toString())
                );
                Element events = document.createElement("events");

                athlete.appendChild(place);
                athlete.appendChild(totalScore);
                athlete.appendChild(events);


                for (DecathlonEventResult eventResult :
                        entry.getAllEventResults()) {

                    Element event = document.createElement("event");
                    event.setAttribute("name", eventResult.getName());

                    String eventResultValue;
                    //1.5km run time should be converted to minutes:seconds
                    if(eventResult.getName().equals(
                            DecathlonEventType.ONE_AND_HALF_KILOMETER_RUN.name()
                    )) {
                        eventResultValue = String.format(
                                "%d:%.2f",
                                eventResult.getResult()
                                                .intValue() / 60,
                                eventResult.getResult() % 60
                        );
                    }
                    else eventResultValue = eventResult.getResult().toString();

                    event.appendChild(
                            document.createTextNode(eventResultValue));
                    events.appendChild(event);
                }

                root.appendChild(athlete);

            }

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult =
                    new StreamResult(new File(outputFilename));

            transformer.transform(domSource, streamResult);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
