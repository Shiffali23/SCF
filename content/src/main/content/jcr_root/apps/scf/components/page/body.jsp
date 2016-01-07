<%@page session="false"%><%
%><%@ include file="/libs/foundation/global.jsp" %><%
%><body class="scf-guide"><div class="screen"><div class="unscreen">
<div class="cg container-fluid">
	<div class="row-fluid">
        <div class="span12">
            <sling:include path="./header"/>
            <div class="banner"><span class="splashtext"><span>Suspendisse malesuada tincidunt ullamcorper.</span><br/> Quisque porttitor elementum dignissim. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris gravida ante lorem, vel pulvinar nunc adipiscing ut. In sit amet neque aliquet metus iaculis consectetur.</span></div>
            <sling:include path="./navigation" resourceType="/apps/scf/components/navigation"/>
            <sling:include path="./content"/>
        </div>
	</div>
</div>
<cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext"/>
</div></div>
<div class="footer">
<div class="row-fluid">
    <div class="span8">
        Ut et lacinia enim. Mauris hendrerit lobortis orci, sed varius ante. Suspendisse viverra turpis eu faucibus consectetur. Donec dapibus facilisis velit, et feugiat enim semper vitae. Nullam blandit facilisis laoreet. Suspendisse ullamcorper pharetra mauris. Cras molestie sodales tortor, tempor commodo mauris mollis feugiat. Phasellus auctor felis eget cursus venenatis. Mauris dapibus purus a massa dictum bibendum.
    </div>
    <div class="span4">
        <ul>
            <li>Quisque</li>
            <li>Vestibulum lacinia</li>
            <li>Phasellus auctor</li>
            <li>Ut eu auctor</li>
            <li>Praesent</li>            
        </ul>
    </div>
</div>
</div>
</body>