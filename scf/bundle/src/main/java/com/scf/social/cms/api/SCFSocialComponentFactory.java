package com.scf.social.cms.api;


import javax.jcr.RepositoryException;

import com.adobe.cq.social.commons.client.api.*;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Component(metatype = false)
public class SCFSocialComponentFactory extends AbstractSocialComponentFactory implements SocialComponentFactory {
    private Logger Log = LoggerFactory.getLogger(SCFSocialComponentFactory.class);
    @Reference
    private CommentSocialComponentListProviderManager commentListProviderManager;

    @Override
    public SocialComponent getSocialComponent(final Resource resource) {
        try {
            return new CustomCommentComponent(resource, this.getClientUtilities(resource.getResourceResolver()), commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public SocialComponent getSocialComponent(final Resource resource, final SlingHttpServletRequest request) {
        try {
            return new CustomCommentComponent(resource, this.getClientUtilities(request), this.getQueryRequestInfo(request), commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public SocialComponent getSocialComponent(Resource resource, ClientUtilities clientUtils, QueryRequestInfo listInfo) {
        try {
            return new CustomCommentComponent(resource, clientUtils, listInfo, commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }


    @Override
    public String getSupportedResourceType() {
        return "scf/components/content/topic";
    }

    @Override
    public int getPriority() {
        return 10;
    }

}
