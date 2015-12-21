
package com.scf.social.cms.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.commons.io.IOUtils;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link org.apache.sling.api.servlets.SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link org.apache.sling.api.servlets.SlingAllMethodsServlet}.
 */
@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/userinfo",methods = {"POST","GET"})

public class BackBoneExampleServlet extends SlingAllMethodsServlet {
    public static final String USER_DATA_PATH = "/content/usergenerated/content";
    private static final String USERDATA = "userdata";

    @Override
    protected void doGet(final SlingHttpServletRequest request,final SlingHttpServletResponse response) throws IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource parentResource = resourceResolver.getResource(USER_DATA_PATH+"/"+USERDATA);
        try {

            JSONArray jsonArray = new JSONArray();
        if(parentResource!=null)
        {
            Iterator<Resource> children = parentResource.listChildren();
            while(children.hasNext())
            {
                ValueMap valueMap = children.next().getValueMap();
                JSONObject childJSON = new JSONObject();
                childJSON.put("id",valueMap.get("id"));
                childJSON.put("author",valueMap.get("author"));
                childJSON.put("title",valueMap.get("title"));
                childJSON.put("url",valueMap.get("url"));
                jsonArray.put(childJSON);

            }
        }
        response.getWriter().println(jsonArray.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {

        String inputData = IOUtils.toString(req.getInputStream());
        ResourceResolver resourceResolver = req.getResourceResolver();
        Resource parentResource = resourceResolver.getResource(USER_DATA_PATH+"/"+ USERDATA);

        try {
            JSONObject jsonObject = new JSONObject(inputData);

            if(parentResource==null)
            {
                Map<String,Object> map = new HashMap<String, Object>();
                parentResource =  resourceResolver.create(resourceResolver.getResource(USER_DATA_PATH),USERDATA,map);


            }

                String id = jsonObject.getString("id");
                String author = jsonObject.getString("author");
                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("id",id);
                map.put("author",author);
                map.put("title",title);
                map.put("url",url);
                resourceResolver.create(parentResource, id, map);
            resourceResolver.commit();
        } catch (JSONException e) {
            e.printStackTrace();
            resp.getWriter().println(e.getMessage());
        }
        resp.getWriter().println("successful");

    }


}



