package com.learningplatform.app.smart_learn.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.learningplatform.app.smart_learn.domain.Message;
import com.learningplatform.app.smart_learn.service.PrimarySequenceService;

@SuppressWarnings("null")
@Component
public class MessageListerner extends AbstractMongoEventListener<Message> {

    @Autowired
    PrimarySequenceService primarySequenceService;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Message> event) {
        if (event.getSource().getMessageId() == null) {
            event.getSource().setMessageId((int) primarySequenceService.getNextValue());
        }
    }

}
