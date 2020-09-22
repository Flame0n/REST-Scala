
async function postData() {
    let response = await fetch('http://localhost:8080/table', {
        method: 'POST'
    });
    
    if(response.ok){
        let a = response.text();
        alert((await a))
    }

}
