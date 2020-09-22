
async function getClientsJson() {
  let response = await fetch('http://localhost:8080/table', {
    method: 'GET'
  });


  if (response.ok) {
    let json = await response.json();
    fillTable(json);
    
  } else {
    alert("Ошибка HTTP: " + response.status);
  }
}