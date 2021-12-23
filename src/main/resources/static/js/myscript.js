// Главная таблица
async function getAllUsers() {
    fetch("/api/persons")
        .then(res => res.json())
        .then(persons => {
            let temp = '';
            persons.forEach(function (persons) {
                temp += `
                <tr>
                <td>${persons.id}</td>
                <td>${persons.name}</td>
                <td>${persons.surname}</td>
                <td>${persons.age}</td>
                <td>${persons.username}</td>
                <td>${persons.roles.map(r => r.name.replace('ROLE_', '')).join(', ')}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#UserEditModal"
                onclick="openModalEdit(${persons.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#UserShowModal"
                onclick="openModalDelete(${persons.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("PersonsTable").innerHTML = temp;
        });
}
getAllUsers()

// -------------- UserShowModal

async function openModalDelete(id){
    let url = '/api/persons/1';
    let response = await fetch(url);
    let personjson = await response.json();


    // fetch("/api/persons/" + id)
    //     .then(res => {
    //     res.json().then(per => {
            document.getElementById("id_edit").value = personjson.id;
            document.getElementById("name").value = personjson.name;
            document.getElementById("surname").value = personjson.surname;
            document.getElementById("username").value = personjson.username;
            document.getElementById("age").value = personjson.age;
    //     })
    // });
}
