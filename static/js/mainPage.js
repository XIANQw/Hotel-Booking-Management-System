$(function () {
    $("#ButtonSearch").click(searchRessource);
});

function searchRessource() {
    $.ajax({
        type: "POST",
        url: "Service?method=createSearchAjax",
        data: $('#FormSearch').serialize(),
        success: function (result, status) {
            var str = result;
            var resp = $.parseJSON(str);
            var html = '<table class="table table-striped"><thead><tr><th scope="col">id</th><th scope="col">Type</th><th scope="col">Price</th><th scope="col">Persons</th><th scope="col">Adresse</th></tr></thead>';
            html += "<tbody>";
            for(var i=0; i<resp.length; i++){
                html += "<tr><td>"+resp[i].id+"</td>"+"<td>"+resp[i].type+"</td>"+"<td>"+resp[i].price+"</td>"+"<td>"+resp[i].person+"</td>"+"<td>"+resp[i].adresse+"</td></tr>";
            }
            html += "</tbody></table>";
            $("#resultOfSearch").html(html);
        }, error: function (res) {
            alert("error="+ res.responseText);
        }
    });
}