package com.hdgi.app.Utills;

import com.hdgi.app.models.Gesture;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

public class OntologyQueryHandler {

    public Gesture readOntology(String gesture) {
        TreeMap<String, String> gestureDetails = new TreeMap<>();
        int keyIndex = 1;
        Gesture newGesture = new Gesture();
        JSONObject jsonObj = new JSONObject();
        newGesture.setName(gesture);
        Model mdl = FileManager.get().loadModel("ontology/HDGI-ontology-4.ttl");

        String qry =
                "PREFIX hdgi: <https://w3id.org/hdgi#>\n" +
                        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                        "\n" +
                        "SELECT *\n" +
                        "WHERE\n" +
                        "{ hdgi:" + gesture + " ?relations ?gestureType .\n" +
                        "}";
        Query query = QueryFactory.create(qry);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, mdl)) {
            ResultSet rs = qexec.execSelect();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ResultSetFormatter.outputAsJSON(outputStream, rs);
//            String json = new String(outputStream.toByteArray());
//            System.out.println(json);
            while (rs.hasNext()) {
                QuerySolution sol = rs.nextSolution();
                Resource predicates = sol.getResource("relations");
                Resource objects = sol.getResource("gestureType");
//                System.out.println(ans.getURI());
//                System.out.println(ans.getString());
                jsonObj.accumulate(predicates.getLocalName(), objects.getLocalName());
                boolean isKeyPresent = gestureDetails.containsKey(predicates.getLocalName());
                if (predicates.getLocalName().equals("type") &&
                        !objects.getLocalName().equals("NamedIndividual")) {
                    newGesture.setGestureCategory("hdgi:" + objects.getLocalName());
                }
                if (isKeyPresent) {
                    if (predicates.getLocalName().equals("type") &&
                            objects.getLocalName().equals("NamedIndividual")) {
                        gestureDetails.put("rdf:" + predicates.getLocalName(),
                                "owl:" + objects.getLocalName());
                    }
                    gestureDetails.put(predicates.getLocalName() + "_" + Integer.toString(keyIndex),
                            objects.getLocalName());
                    keyIndex += 1;
                } else {
                    if (predicates.getLocalName().equals("type") &&
                            objects.getLocalName().equals("NamedIndividual")) {
                        gestureDetails.put("rdf:" + predicates.getLocalName(),
                                "owl:" + objects.getLocalName());
                    } else if (!predicates.getLocalName().equals("type")) {
                        gestureDetails.put(predicates.getLocalName(),
                                objects.getLocalName());
                    }
                }
            }
            if (gestureDetails.isEmpty()) {
                gestureDetails.put("Details", "Not available");
            }
            newGesture.setGestureDetails(gestureDetails);
            return newGesture;
        }
    }

    public ArrayList<String> extractAllGestures() {
        ArrayList<String> gestures = new ArrayList<String>();
        Model mdl = FileManager.get().loadModel("ontology/HDGI-ontology-4.ttl");

        String qry =
                "PREFIX hdgi: <https://w3id.org/hdgi#>\n" +
                        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                        "\n" +
                        "SELECT ?allGestures\n" +
                        "WHERE\n" +
                        "{\n" +
                        "{?allGestures rdf:type hdgi:ForearmGesture  .}\n" +
                        "UNION\n" +
                        "{?allGestures rdf:type hdgi:UpperArmGesture  .}\n" +
                        "UNION\n" +
                        "{?allGestures rdf:type hdgi:HandGesture  .}\n" +
                        "UNION\n" +
                        "{?allGestures rdf:type hdgi:PalmGesture  .}\n" +
                        "UNION\n" +
                        "{?allGestures rdf:type hdgi:FingerGesture  .}\n" +
                        "UNION\n" +
                        "{?allGestures rdf:type hdgi:Gesture  .}\n" +
                        "}";
        Query query = QueryFactory.create(qry);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, mdl)) {
            ResultSet rs = qexec.execSelect();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ResultSetFormatter.outputAsJSON(outputStream, rs);
//            String json = new String(outputStream.toByteArray());
//            System.out.println(json);
            while (rs.hasNext()) {
                QuerySolution sol = rs.nextSolution();
                Resource subjects = sol.getResource("allGestures");
                gestures.add("hdgi:"+subjects.getLocalName());
//                System.out.println(subjects.toString());
            }
        }
        return gestures;
    }
}
