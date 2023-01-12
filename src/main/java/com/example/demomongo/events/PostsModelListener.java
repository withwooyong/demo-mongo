package com.example.demomongo.events;

import com.example.demomongo.domain.Posts;
import com.example.demomongo.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostsModelListener extends AbstractMongoEventListener<Posts> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Posts> event) {
        event.getSource().setId(sequenceGenerator.generateSequence(Posts.SEQUENCE_NAME));
    }
}
