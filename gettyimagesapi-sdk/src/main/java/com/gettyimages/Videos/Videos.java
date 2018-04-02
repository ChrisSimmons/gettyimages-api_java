package com.gettyimages.Videos;

import com.gettyimages.AbstractApiRequest;
import com.gettyimages.Constants;
import com.gettyimages.Credentials;
import com.gettyimages.SdkException;

import java.util.ArrayList;
import java.util.List;

public class Videos extends AbstractApiRequest {
    private String MustSpecifyAtLeastOneImageIdMessage = "Must specify at least one image id.";
    private String VideosPath = "/videos";
    private List<String> videoIds = new ArrayList<String>();

    private Videos(Credentials credentials, String baseUrl)
    {
        super(credentials, baseUrl);
    }

    public static Videos GetInstance(Credentials credentials, String baseUrl)
    {
        return new Videos(credentials, baseUrl);
    }

    public String executeAsync() throws SdkException {
        if (videoIds.isEmpty())
        {
            throw new SdkException(MustSpecifyAtLeastOneImageIdMessage);
        }
        method = "GET";
        path = VideosPath;
        if (videoIds.size() > 1 ) {
            queryParams.put(Constants.IdsParameterName, videoIds);
        }
        else {
            String id = String.join(",", videoIds);
            path = VideosPath + "/" + id;
        }

        return super.executeAsync();
    }

    public Videos withId(String val)
    {
        videoIds.add(val);
        return this;
    }

    public Videos withIds(List<String> ids)
    {
        videoIds.addAll(ids);
        return this;
    }

    public Videos withResponseFields(Iterable<String> values)
    {
        addResponseFields(values);
        return this;
    }
}
