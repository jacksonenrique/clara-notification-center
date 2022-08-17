package com.clara.notification.center.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "Message", schema = "notificationCenter", catalog = "")

@Getter
@Setter
@NoArgsConstructor
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int intId;

    @Basic
    @Column(name = "deduplicate_id", nullable = true, length = 50)
    private String strDeduplicateId;

    @Basic
    @Column(name = "msg_template_id", nullable = false)
    private int intTemplateId;

    @Basic
    @Column(name = "action_id", nullable = false)
    private int intActionId;

    @Basic
    @Column(name = "customer_id", nullable = false)
    private int intCustomerId;

    @Basic
    @Column(name = "recipient", nullable = true, length = 200 )
    private String strRecipient;

    @Basic
    @Column(name = "subject", nullable = true, length = 200 )
    private String strSubject;

    @Basic
    @Column(name = "metrics", nullable = true, length = 200)
    private String strMetrics;

    @Basic
    @Column(name = "created", nullable = true, length = 50)
    private String strCreated;

    @Basic
    @Column(name = "failure_message", nullable = true, length = 200)
    private String strFailureMessage;

    @Basic
    @Column(name = "type", nullable = true, length = 50)
    private String strType;

    @Basic
    @Column(name = "newsletter_id", nullable = false)
    private int intNewsletterId;

    @Basic
    @Column(name = "content_id", nullable = false)
    private int intContentId;

    @Basic
    @Column(name = "campaign_id", nullable = false)
    private int intCampaignId;

    @Basic
    @Column(name = "broadcast_id", nullable = false)
    private int intBroadcastId;

    @Basic
    @Column(name = "forgotten", nullable = false)
    private Boolean blnForgotten;


}

