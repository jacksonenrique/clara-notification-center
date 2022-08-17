package com.clara.notification.center.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {

    private int intId;
    private String strDeduplicateId;
    private int intTemplateId;
    private int intActionId;
    private int intCustomerId;
    private String strRecipient;
    private String strSubject;
    private String strMetrics;
    private String strCreated;
    private String strFailureMessage;
    private String strType;
    private int intNewsletterId;
    private int intContentId;
    private int intCampaignId;
    private int intBroadcastId;
    private Boolean blnForgotten;

}
