package com.jmms.api.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.MDC;
import com.jmms.api.domain.common.Application;
import com.jmms.api.domain.common.Request;
import com.jmms.api.domain.common.ResponseAnnotation;
import com.jmms.api.domain.common.ResponseInformation;
import com.jmms.api.utils.Config;
import com.jmms.api.utils.Constants;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


@Provider
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class GsonProvider<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

    private static final Config config = Config.getInstance();

    private final Gson gson;


    @Context
    private UriInfo ui;

    public GsonProvider() {
        GsonBuilder builder = new GsonBuilder()
                .serializeNulls()
                .enableComplexMapKeySerialization();

        this.gson = builder.create();
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                      MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {

        InputStreamReader reader = new InputStreamReader(entityStream, "UTF-8");
        try {
            if (annotations != null && annotations.length >= 1) {
                if (annotations[0].getClass() == ResponseAnnotation.class) {
                    ResponseAnnotation annotation = (ResponseAnnotation) annotations[0];
                    Type fixedType = TypeFactory.getType(annotation.getGenericType());
                    if (fixedType != null) {
                        return gson.fromJson(reader, fixedType);
                    }
                }
            }

            return gson.fromJson(reader, type);
        }catch (Exception e){
            return null;
        } finally {
            reader.close();
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {

        PrintWriter printWriter = new PrintWriter(entityStream);
        try {
            ResponseInformation<T> responseInformation = getResponseInformation(t);

            String json = gson.toJson(responseInformation);

            printWriter.write(json);
            printWriter.flush();
        } finally {
            printWriter.close();
        }
    }

    private ResponseInformation<T> getResponseInformation(T t) {
        ResponseInformation<T> responseInformation = new ResponseInformation<>();
        Application application = new Application();
        application.setName(config.getAppName());
        application.setTime(System.currentTimeMillis());
        application.setVersion(config.getAppVersion());
        application.setBuild(config.getBuildVersion());
        responseInformation.setApplication(application);

        Request request = new Request();
        request.setRequestedUrl(ui.getRequestUri());

        Object requestId = MDC.get(Constants.REQUEST_ID_KEY);
        if (requestId != null) {
            request.setId(requestId.toString());
        }

        responseInformation.setRequest(request);

        responseInformation.setResponse(t);
        return responseInformation;
    }

}
