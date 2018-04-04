package com.malinovski.helpdesk.util.helper.impl;

import com.google.gson.*;
import com.malinovski.helpdesk.dto.TicketDto;
import com.malinovski.helpdesk.util.helper.RequestHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;

@Component
public final class RequestHelperImpl implements RequestHelper {

    public TicketDto getTicketDtoFromRequest(HttpServletRequest request) {
        MultipartHttpServletRequest mRequest;
        TicketDto ticketDto = null;
        try {
            mRequest = (MultipartHttpServletRequest) request;
            mRequest.getParameterMap();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });
            Gson gson = builder.create();
            ticketDto = gson.fromJson(mRequest.getParameter("ticket"), TicketDto.class);

            Iterator itr = mRequest.getFileNames();
            while (itr.hasNext()) {
                MultipartFile mFile = mRequest.getFile((String) itr.next());
                ticketDto.addAttachment(mFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketDto;
    }
}
