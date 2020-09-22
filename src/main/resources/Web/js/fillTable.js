
var clientsTable = document.getElementById("clientsTable");

function fillTable(json) {
    for(var i = 0; i < json.lenght; i++){
        json[i].id = 0;
        json[i].firstName = "";
        json[i].lastName = "";
        json[i].birthDate = "";
        json[i].address = "shiiiit";
    }
}