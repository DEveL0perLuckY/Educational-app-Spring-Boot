package com.learningplatform.app.smart_learn.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Message {

    @Id
    private Integer messageId;

    private String content;
    @DBRef
    private User receiver;

    @DBRef
    private User sender;

    private LocalDateTime timestamp;

}
