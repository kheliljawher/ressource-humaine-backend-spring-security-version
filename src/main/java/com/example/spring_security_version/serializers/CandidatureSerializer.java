package com.example.spring_security_version.serializers;


import com.example.spring_security_version.entity.Candidature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CandidatureSerializer extends StdSerializer<Candidature> {

    public CandidatureSerializer() {
        this(null);
    }

    public CandidatureSerializer(Class<Candidature> t) {
        super(t);
    }

    @Override
    public void serialize(
            Candidature item,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        Candidature candidature=new Candidature();
        candidature.setTitre(item.getTitre());
        candidature.setDescription(item.getDescription());
        candidature.setDepartement(item.getDepartement());
        generator.writeObject(candidature);
    }
}