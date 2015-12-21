<%@page session="false"%>

<%@include file="/apps/scf/global.jsp" %>
<cq:includeClientLib css="apps.backbonedemo" />
<body>
<div class="container">
    <h1>Backbone Example Application</h1>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>Author</th>
            <th>Title</th>
            <th>URL</th>
            <th>Action</th>
        </tr>
        <tr>
            <td><input class="form-control id-input"></td>
            <td><input class="form-control author-input"></td>
            <td><input class="form-control title-input"></td>
            <td><input class="form-control url-input"></td>
            <td><button class="btn btn-primary add-blog">Add</button></td>
        </tr>
        </thead>
        <tbody class="blogs-list"></tbody>
    </table>
</div>

<script type="text/template" class="blogs-list-template">
    <td><span class="author">{{id}}</span></td>
    <td><span class="author">{{author}}</span></td>
    <td><span class="title">{{title}}</span></td>
    <td><span class="url">{{url}}</span></td>
    <td><button class="btn btn-warning edit-blog">Edit</button> <button class="btn btn-danger delete-blog">Delete</button><button class="btn btn-success update-blog" style="display:none">Update</button> <button class="btn btn-danger cancel" style="display:none">Cancel</button></td>

</script>
<cq:includeClientLib js="apps.backbonedemo" />

</body>
