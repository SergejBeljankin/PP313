async function getAllUsers() {
    // let url = '/api/persons/';
    // let response = await fetch(url);
    //
    // let personjson = await response.json(); // читаем ответ в формате JSON
    //
    // console.log(personjson);
 // ****************************************************************

    fetch("/api/persons")
        .then(res => res.json())
        .then(persons => {
            let temp = '';
            persons.forEach(function (persons) {
                temp += `
                <tr>
                <td id="id${persons.id}">${persons.id}</td>
                <td id="firstName${persons.id}">${persons.name}</td>
                <td id="lastName${persons.id}">${persons.surname}</td>
                <td id="age${persons.id}">${persons.age}</td>
                <td id="email${persons.id}">${persons.username}</td>
                <td id="roles${persons.id}">${persons.roles.map(r => r.name.replace('ROLE_', '')).join(', ')}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#modalEdit"
                onclick="openModal(${persons.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#modalDelete"
                onclick="openModal(${persons.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("PersonsTable").innerHTML = temp;
        });
}

getAllUsers()