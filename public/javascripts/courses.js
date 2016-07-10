$( document ).ready(function() {
    $.getJSON("/getCourses", function(data){
        $.each(data, function(k,v) {
            $('#courses tr:last').after(
            "<tr>"+
                "<td>"
                    +v.id+
                "</td><td>"
                    +v.name+
                "</td><td>"
                     +v.students+
                "</td><td>"
                    +"<div class=\"pull-right\">"+
                        "<button type=\"button\" class=\"btn btn-info pull-right\" data-toggle=\"modal\" data-target=\"#updateModel\" data-Id=\""+v.id+"\">Update</button>"
                        +
                        "<button type=\"button\" class=\"btn btn-info pull-right\" data-toggle=\"modal\" data-target=\"#deleteModel\" data-Id=\""+v.id+"\">Delete</button>"
                    +"</div>"+
                "</td></tr>")
        })
    });

    $('#updateModel, #deleteModel').on('show.bs.modal', function(e) {
        $.getJSON("/getCourse/"+$(e.relatedTarget).data('id'), function(data){
              $(e.currentTarget).find('input[name="id"]').val(data.id);
              $(e.currentTarget).find('input[name="name"]').val(data.name);
        })
    });

    $('#createModel, #updateModel, #deleteModel').on('show.bs.modal', function(e) {
        $.getJSON("/getStudents", function(data){
            $.each(data, function(k,v) {
                $(e.currentTarget).find('select[name="students[]"]')
                    .append($("<option></option>")
                    .attr("value",v)
                    .text(v.name));
            })
        });
        //$(e.currentTarget).find('select[name="students[]"]').chosen({no_results_text: "Oops, nothing found!", width: "100%"});
    });
});