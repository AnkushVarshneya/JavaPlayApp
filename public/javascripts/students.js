$( document ).ready(function() {
    $.getJSON("/getStudents", function(data){
        $.each(data, function(k,v) {
            $('#students tr:last').after(
            "<tr>"+
                "<td>"
                    +v.id+
                "</td><td>"
                    +v.name+
                "</td><td>"
                     +v.courses+
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
        $.getJSON("/getStudent/"+$(e.relatedTarget).data('id'), function(data){
              $(e.currentTarget).find('input[name="id"]').val(data.id);
              $(e.currentTarget).find('input[name="name"]').val(data.name);
        })
    });

    $.getJSON("/getCourses", function(data){
        $.each(data, function(k,v) {
            $('#createModel, #updateModel, #deleteModel').find('select[name="student.courses[]"]')
                .append($("<option></option>")
                .attr("value",v.id)
                .text(v.name));
        })
    });

    //$('#createModel, #updateModel, #deleteModel').find('select[name="student.courses[]"]').chosen({no_results_text: "Oops, nothing found!", width: "100%"});
});