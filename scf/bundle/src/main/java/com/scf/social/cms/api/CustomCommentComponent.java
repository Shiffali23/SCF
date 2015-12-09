package com.scf.social.cms.api;

import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.Resource;
import com.adobe.cq.social.commons.client.api.ClientUtilities;
import com.adobe.cq.social.commons.client.api.QueryRequestInfo;
import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import com.adobe.cq.social.forum.client.api.AbstractPost;
import com.adobe.cq.social.forum.client.api.ForumConfiguration;
import com.adobe.cq.social.forum.client.api.Post;

public class CustomCommentComponent extends AbstractPost<ForumConfiguration> implements Post<ForumConfiguration> {

    public CustomCommentComponent(final Resource resource, final ClientUtilities clientUtils,
                               final CommentSocialComponentListProviderManager commentListProviderManager) throws RepositoryException{
        super(resource, clientUtils, commentListProviderManager);

    }

    public CustomCommentComponent(final Resource resource, final ClientUtilities clientUtils,
                               final QueryRequestInfo queryInfo, final CommentSocialComponentListProviderManager commentListProviderManager)
            throws RepositoryException {
        super(resource, clientUtils, queryInfo, commentListProviderManager);

    }


    public String getStatus() {
        return "New Blog";
    }

}
