
var refreshButton = document.getElementById("refreshButton");

var addButton = document.getElementById("addButton");

var deleteButton = document.getElementById("deleteButton");

var updateButton = document.getElementById("updateButton");

refreshButton.onclick = function () {
    getClientsJson();
}


addButton.onclick = function () {
    postData();
}

deleteButton.onclick = function () {

}

updateButton.onclick = function () {

}