package com.example.spring_security_version.serializers;

import com.example.spring_security_version.entity.Candidature;
import com.example.spring_security_version.entity.Utilisateur;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListUtilisateurSerializer extends StdSerializer<List<Utilisateur>> {

    public ListUtilisateurSerializer() {
        this(null);
    }

    public ListUtilisateurSerializer(Class<List<Utilisateur>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Utilisateur> items,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {
        List<Utilisateur> ids = new ArrayList<>();
        if(items != null){
            for(Utilisateur item : items) {
                Utilisateur utilisateur=new Utilisateur();
                utilisateur.setId(item.getId());
                utilisateur.setCin(item.getCin());
                ids.add(utilisateur);
            }
        }

        generator.writeObject(ids);
    }
}
