async function getAllUsers() {
    let url = '/api/persons/';
    let response = await fetch(url);

    let personjson = await response.json(); // читаем ответ в формате JSON

    console.log(personjson);
    // fetch("/api/persons")
        // .then(res => res.json())
        // .then(persons => {
        //     let temp = '';
        //     persons.forEach(function (person) {
        //         temp += `
        //         <tr>
        //         <td id="id${person.id}">${person.id}</td>
        //         <td id="firstName${person.id}">${person.name}</td>
        //         <td id="lastName${person.id}">${person.surname}</td>
        //         <td id="age${person.id}">${person.age}</td>
        //         <td id="email${person.id}">${person.username}</td>
        //         <td id="roles${person.id}">${person.roles.map(r => r.roles.replace('ROLE_', '')).join(', ')}</td>
        //         <td>
        //         <button class="btn btn-info btn-md" type="button"
        //         data-toggle="modal" data-target="#modalEdit"
        //         onclick="openModal(${person.id})">Edit</button></td>
        //         <td>
        //         <button class="btn btn-danger btn-md" type="button"
        //         data-toggle="modal" data-target="#modalDelete"
        //         onclick="openModal(${person.id})">Delete</button></td>
        //       </tr>`;
        //     });
        //     document.getElementById("PersonsTable").innerHTML = temp;
        // });
}

getAllUsers()