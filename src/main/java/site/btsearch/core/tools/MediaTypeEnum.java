package site.btsearch.core.tools;

import okhttp3.MediaType;

public enum MediaTypeEnum {

    JSON(MediaType.parse("application/json")),
    TEXT(MediaType.parse("plain/text")),
    HTML(MediaType.parse("text/html")),
    XML(MediaType.parse("application/xml"));

    private MediaType mediaType;

    private MediaTypeEnum(MediaType mediaType){
        this.mediaType = mediaType;
    }

    public MediaType getMediaType(){
        return mediaType;
    }

}
