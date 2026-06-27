
package org.application.services.dto;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SendTextMessageDTO {

    public SendTextMessageDTO(String chatId, String text, String session) {
        this.chatId = chatId;
        this.text = text;
        this.session = session;
        this.linkPreview = true;
        this.linkPreviewHighQuality = false;
    }

    @SerializedName("chatId")
    @Expose
    private String chatId;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("reply_to")
    @Expose
    private Object replyTo;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("linkPreview")
    @Expose
    private Boolean linkPreview;
    @SerializedName("linkPreviewHighQuality")
    @Expose
    private Boolean linkPreviewHighQuality;
    @SerializedName("session")
    @Expose
    private String session;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Object replyTo) {
        this.replyTo = replyTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getLinkPreview() {
        return linkPreview;
    }

    public void setLinkPreview(Boolean linkPreview) {
        this.linkPreview = linkPreview;
    }

    public Boolean getLinkPreviewHighQuality() {
        return linkPreviewHighQuality;
    }

    public void setLinkPreviewHighQuality(Boolean linkPreviewHighQuality) {
        this.linkPreviewHighQuality = linkPreviewHighQuality;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

}
